package com.sirh.mqd.reporting.webapp.views.menu;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.xerces.parsers.DOMParser;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sirh.mqd.reporting.api.resources.ILabelSourceBundle;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.constantes.MenuConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.resources.LabelSourceBundle;

/**
 * Vue du Menu principal de l'application
 *
 * @author alexandre
 */
@Named(ViewConstantes.MENU_BEAN)
@RequestScoped
public class MenuBean implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 1141496229132522108L;

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuBean.class);

	/**
	 * Séparateur de la liste des roles
	 */
	private static final String ROLES_SEPARATOR = ",";

	/**
	 * Liste des roles selon l'authentification
	 */
	private List<String> authorities;

	/**
	 * Service de gestion des messages
	 */
	@Inject
	@Qualifier(ContextConstantes.MESSAGE)
	private IMessageSourceBundle messagesBundle;

	/**
	 * Service de gestion des libellés
	 */
	private ILabelSourceBundle labelsBundle;

	/**
	 * Modèle du menu haut
	 */
	private MenuModel modelMenu;

	/**
	 * Page du menu actuellement sélectionnée
	 */
	private String currentMenu;

	/**
	 * Constructeur par défaut
	 */
	public MenuBean() {
		super();
	}

	/**
	 * Création du menu PrimeFaces pour le menu principal.
	 */
	public void initMenu() {
		this.modelMenu = new DefaultMenuModel();
		final FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			this.labelsBundle = context.getApplication().evaluateExpressionGet(context,
					"#{" + ContextConstantes.LABEL + "}", LabelSourceBundle.class);
			this.currentMenu = context.getExternalContext().getRequestParameterMap().get(MenuConstantes.ARIANE_ATTR_ID);
		}

		try {
			parseXmlMenuFile();
		} catch (final SAXException e) {
			LOGGER.error("[MENU] Erreur interne", e);
		} catch (final IOException e) {
			LOGGER.error("[MENU] Erreur interne", e);
		}
	}

	/**
	 * Ajoute un élément (MenuItem ou Submenu) au modèle du menu
	 *
	 * @param menuParent
	 *            Composant JSF parent de l'élément à ajouter. Si null, alors
	 *            menu de premier niveau.
	 * @param uiComponent
	 *            Composant JSF à ajouter
	 */
	public void addElementMenu(final MenuElement menuParent, final MenuElement component) {
		if (menuParent == null) {
			if (component instanceof MenuItem) {
				this.modelMenu.addElement(component);
				LOGGER.debug(this.messagesBundle.getMessage("menu.add.menu",
						new Object[] { String.valueOf(((MenuItem) component).getValue()) }));
			} else {
				this.modelMenu.addElement(component);
				LOGGER.debug(this.messagesBundle.getMessage("menu.add.submenu",
						new Object[] { ((Submenu) component).getLabel() }));
			}
		} else {
			if (menuParent instanceof DefaultSubMenu) {
				((DefaultSubMenu) menuParent).getElements().add(component);
			}
		}
	}

	/**
	 * Méthode de recherche des noeuds, issue du fichier XML <b>menu.xml</b>
	 *
	 * @throws SAXException
	 * @throws IOException
	 */
	public void parseXmlMenuFile() throws SAXException, IOException {
		retrieveRolesGranted();

		final DOMParser parser = new DOMParser();

		final URL url = getClass().getResource(MenuConstantes.XML_FILE);
		parser.parse(url.getFile());

		final Document document = parser.getDocument();
		final Element element = document.getDocumentElement();
		processNode(null, element.getChildNodes());
	}

	/**
	 * Méthode permettant de récupérer les roles autorisés à accéder aux menus
	 */
	private void retrieveRolesGranted() {
		this.authorities = new ArrayList<String>();
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
			for (final GrantedAuthority authority : authorities) {
				this.authorities.add(authority.getAuthority());
			}
		}
	}

	/**
	 * Méthode récursive de recherche des noeuds
	 *
	 * @param menuParent
	 *            le menu parent associé à la liste des noeuds
	 * @param liste
	 *            la liste de noeuds
	 */
	private void processNode(final MenuElement menuParent, final NodeList liste) {
		if (liste != null) {
			Node child;
			MenuElement component;
			for (int i = 0; i < liste.getLength(); i++) {
				child = liste.item(i);
				if (Node.ELEMENT_NODE == child.getNodeType()) {
					component = generateElementMenu(child);
					if (component != null) {
						this.addElementMenu(menuParent, component);
						if (child.hasChildNodes()) {
							processNode(component, child.getChildNodes());
						}
					}
				}
			}
		}
	}

	/**
	 * Méthode générant les éléments Primefaces du menu à partir des
	 * informations du noeud XML.
	 *
	 * @param node
	 *            le noeud XML contenant les informations à paramétrer
	 * @return UIComponent l'élément généré (MenuItem ou Submenu)
	 */
	private MenuElement generateElementMenu(final Node node) {
		if (node.hasAttributes()) {
			final NamedNodeMap attributes = node.getAttributes();

			if (MenuConstantes.XML_MENU.equals(node.getNodeName())) {
				final String roles = attributes.getNamedItem(MenuConstantes.ATTR_ACCESS).getNodeValue();
				if (isAccessGranted(roles)) {
					final String menuItemId = attributes.getNamedItem(MenuConstantes.ATTR_ID).getNodeValue();
					final DefaultMenuItem menuItem = new DefaultMenuItem();
					menuItem.setId(MenuConstantes.PREFIX_ID + menuItemId);
					menuItem.setValue(this.labelsBundle
							.getLabel(attributes.getNamedItem(MenuConstantes.ATTR_KEY).getNodeValue()));

					final String menuItemUrl = attributes.getNamedItem(MenuConstantes.ATTR_URL).getNodeValue();
					if (StringUtils.isNotBlank(menuItemUrl)) {
						menuItem.setUrl(menuItemUrl + "?" + MenuConstantes.ARIANE_ATTR_ID + "=" + menuItemId);
					}
					if (StringUtils.isNotBlank(this.currentMenu) && menuItemId.equals(this.currentMenu)) {
						menuItem.setContainerStyleClass("ui-menuitem-selected");
					}

					return menuItem;
				}
			} else if (MenuConstantes.XML_SUBMENU.equals(node.getNodeName())) {
				final String roles = attributes.getNamedItem(MenuConstantes.ATTR_ACCESS).getNodeValue();
				if (isAccessGranted(roles)) {
					final String subMenuId = attributes.getNamedItem(MenuConstantes.ATTR_ID).getNodeValue();
					final DefaultSubMenu subMenu = new DefaultSubMenu();
					subMenu.setId(MenuConstantes.PREFIX_ID + subMenuId);
					subMenu.setLabel(this.labelsBundle
							.getLabel(attributes.getNamedItem(MenuConstantes.ATTR_KEY).getNodeValue()));

					return subMenu;
				}
			}
		}
		return null;
	}

	/**
	 * Méthode permettant de savoir si la liste des roles définis autorise
	 * l'affichage du menu ou sous-menu.
	 *
	 * @param roles
	 *            liste des roles séparés par une virgule (sans espace)
	 * @return boolean true si autorisé, false sinon
	 */
	private boolean isAccessGranted(final String roles) {
		for (final String role : roles.split(ROLES_SEPARATOR)) {
			if (this.authorities.contains(role.trim())) {
				return true;
			}
		}
		return false;
	}

	public MenuModel getModelMenu() {
		return modelMenu;
	}

	public void setModelMenu(final MenuModel modelMenu) {
		this.modelMenu = modelMenu;
	}

	public String getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(final String currentMenu) {
		this.currentMenu = currentMenu;
	}

	public IMessageSourceBundle getMessagesBundle() {
		return messagesBundle;
	}

	public void setMessagesBundle(final IMessageSourceBundle messagesBundle) {
		this.messagesBundle = messagesBundle;
	}
}
