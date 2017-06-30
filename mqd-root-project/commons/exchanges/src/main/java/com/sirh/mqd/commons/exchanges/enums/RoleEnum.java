package com.sirh.mqd.commons.exchanges.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.utils.GenericUtils;

/**
 * Enumération listant les roles pour authentifier un utilisateur
 *
 * @author alexandre
 */
public enum RoleEnum {

	ROLE_USER("Pilotage", "Gestionnaire"),

	ROLE_ADMIN("Pilotage");

	public static final List<RoleEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

	private String[] roles;

	RoleEnum(final String... roles) {
		this.roles = roles;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(final String[] roles) {
		this.roles = GenericUtils.clonerObjTableau(roles);
	}

	/**
	 * Parse le rôle dans un élément de cette énumération.
	 *
	 * @param libelle
	 *            prend comme valeurs un des libellés des énumérations.
	 * @return {@link List} la liste des rôles Spring Security associés au nom
	 *         du rôle en entrée
	 */
	public static List<RoleEnum> enumOf(final String role) {
		final List<RoleEnum> roles = new ArrayList<RoleEnum>();
		final Iterator<RoleEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final RoleEnum perimetre = iter.next();
			if (ArrayUtils.contains(perimetre.getRoles(), StringUtils.normalizeSpace(role))) {
				roles.add(perimetre);
			}
		}
		if (roles.isEmpty()) {
			throw new IllegalArgumentException("Impossible de convertir un élément d'RoleEnum à partir du rôle : '"
					+ StringUtils.normalizeSpace(role) + "'");
		} else {
			return roles;
		}
	}
}
