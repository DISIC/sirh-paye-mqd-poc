package com.sirh.mqd.reporting.persistence.dao.impl;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.wildcardQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.common.base.Strings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.SpanOrQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.DisjunctionQueryClause;
import com.sirh.mqd.commons.exchanges.enums.EnumErreursMetier;
import com.sirh.mqd.commons.exchanges.enums.EnumErrorCondition;
import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;
import com.sirh.mqd.reporting.persistence.dao.IElasticsearchDAO;
import com.sirh.mqd.reporting.persistence.entities.IHMActionEntity;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierErrorEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierSuccessEntity;
import com.sirh.mqd.reporting.persistence.entities.WSPerformanceEntity;

@Component
public class ElasticsearchDAO implements IElasticsearchDAO<WSGenericMetierEntity> {

	/**
	 * Nombre de résultat maximum par page
	 */
	private static final int MAX_RESULT_PER_PAGE = 300000;

	@Autowired
	private ElasticsearchTemplate template;

	@Override
	public Page<WSGenericMetierEntity> findAll() {

		/**
		 * Requete sans query car aucun filtre
		 */
		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findEchange(final Date from, final Date to) {

		final BoolQueryBuilder builder = boolQuery();

		final NativeSearchQueryBuilder nativeQuery = new NativeSearchQueryBuilder();

		if (from != null && to != null) {
			nativeQuery.withQuery(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findByService(final String serviceName, final Date from, final Date to) {

		if (serviceName == null) {
			throw new NullPointerException("Le parametre serviceName est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(termQuery(PersistenceReportingConstantes.SERVICE, serviceName));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<IHMActionEntity> findByActionNature(final String nature, final Date from, final Date to) {

		if (nature == null) {
			throw new NullPointerException("Le parametre nature est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(termQuery(PersistenceReportingConstantes.ACTION_NATURE, nature));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<IHMActionEntity> page = template.queryForPage(searchQuery, IHMActionEntity.class);

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.TIMESTAMP));
			page = template.queryForPage(searchQuery, IHMActionEntity.class);
		}

		return page;
	}

	@Override
	public FacetedPage<WSGenericMetierEntity> findNotification(final Date from, final Date to) {

		final BoolQueryBuilder builder = boolQuery();

		builder.must(termQuery(PersistenceReportingConstantes.MODE, ModeEchange.NOTIFICATION.name()));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		FacetedPage<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		FacetedPage<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		FacetedPage<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new FacetedPageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new FacetedPageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new FacetedPageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findByServiceAndSens(final ModeEchange mode, final ModuleEnum composant, final String service,
			final String sens, final Date from, final Date to) {

		if (mode == null) {
			throw new NullPointerException("Le parametre mode est null");
		}
		if (composant == null) {
			throw new NullPointerException("Le parametre composant est vide");
		}

		if (Strings.isNullOrEmpty(service)) {
			throw new NullPointerException("Le parametre service est vide");
		}

		if (Strings.isNullOrEmpty(sens)) {
			throw new NullPointerException("Le parametre sens est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(wildcardQuery(PersistenceReportingConstantes.MODE, "*" + mode.name() + "*"));
		builder.must(termQuery(PersistenceReportingConstantes.SERVICE, service));
		builder.must(wildcardQuery(PersistenceReportingConstantes.CLASSNAME, "*" + composant.name().toLowerCase() + "*"));
		builder.must(termQuery(PersistenceReportingConstantes.SENS, sens));
		builder.must(termQuery(PersistenceReportingConstantes.CODE_ERREUR_IVTR, PersistenceReportingConstantes.NULL_VALUE));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findByServiceAndSensAndStatus(final ModeEchange mode, final ModuleEnum composant, final String service,
			final String sens, final Boolean status, final Date from, final Date to) {

		if (mode == null) {
			throw new NullPointerException("Le parametre mode est null");
		}
		if (composant == null) {
			throw new NullPointerException("Le parametre composant est vide");
		}

		if (Strings.isNullOrEmpty(service)) {
			throw new NullPointerException("Le parametre service est vide");
		}

		if (Strings.isNullOrEmpty(sens)) {
			throw new NullPointerException("Le parametre sens est vide");
		}

		if (status == null) {
			throw new NullPointerException("Le parametre status est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(wildcardQuery(PersistenceReportingConstantes.MODE, "*" + mode.name() + "*"));
		builder.must(termQuery(PersistenceReportingConstantes.SERVICE, service));
		builder.must(wildcardQuery(PersistenceReportingConstantes.CLASSNAME, "*" + composant.name().toLowerCase() + "*"));
		builder.must(termQuery(PersistenceReportingConstantes.SENS, sens));
		builder.must(termQuery(PersistenceReportingConstantes.STATUS, status));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findByErreurMetier(final String sens, final List<EnumErreursMetier> erreurMetierList,
			final Date from, final Date to) {

		if (sens == null) {
			throw new NullPointerException("Le parametre sens est null");
		}
		if (erreurMetierList == null || erreurMetierList.isEmpty()) {
			throw new NullPointerException("Le parametre erreurMetierList est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(disjuctionQuery(erreurMetierList, PersistenceReportingConstantes.CODE_ERREUR_IVTR));

		builder.must(termQuery(PersistenceReportingConstantes.SENS, sens));
		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuerySuccess = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();
		final SearchQuery searchQueryError = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> pageSuccess = template.queryForPage(searchQuerySuccess, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> pageError = template.queryForPage(searchQueryError, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> pageResult = null;

		if (pageSuccess != null && pageSuccess.hasContent() && pageSuccess.getTotalElements() > 0L) {
			searchQuerySuccess.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			pageSuccess = template.queryForPage(searchQuerySuccess, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> listSuccess = new ArrayList<WSGenericMetierEntity>(pageSuccess.getContent());
			if (pageError != null && pageError.hasContent() && pageError.getTotalElements() > 0L) {
				searchQueryError.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				pageError = template.queryForPage(searchQueryError, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> listError = new ArrayList<WSGenericMetierEntity>(pageError.getContent());
				listSuccess.addAll(listError);
				pageResult = new PageImpl<WSGenericMetierEntity>(listSuccess);
			} else {
				final List<WSGenericMetierEntity> listResult = new ArrayList<WSGenericMetierEntity>();
				listResult.addAll(listSuccess);
				pageResult = new PageImpl<WSGenericMetierEntity>(listResult);
			}
		}

		else {
			if (pageError != null && pageError.hasContent() && pageError.getTotalElements() > 0L) {
				searchQueryError.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				pageError = template.queryForPage(searchQueryError, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> listResult = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> listError = new ArrayList<WSGenericMetierEntity>(pageError.getContent());
				listResult.addAll(listError);
				pageResult = new PageImpl<WSGenericMetierEntity>(listResult);
			}
		}

		if (pageResult == null) {
			final SearchQuery emptyQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			pageResult = template.queryForPage(emptyQuery, WSGenericMetierEntity.class);

		}

		return pageResult;
	}

	@Override
	public Page<WSGenericMetierEntity> findByErreurCondition(final List<EnumErrorCondition> erreurConditionList, final Date from,
			final Date to) {

		if (erreurConditionList == null || erreurConditionList.isEmpty()) {
			throw new NullPointerException("Le parametre erreurConditionList est vide");
		}

		final BoolQueryBuilder builder = boolQuery();
		builder.must(disjuctionQuery(erreurConditionList, PersistenceReportingConstantes.ERROR_CONDITION_STRUCTURE));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findByErreurConditionAndService(final List<EnumErrorCondition> erreurConditionList,
			final List<EnumService> serviceList, final Date from, final Date to) {

		if (erreurConditionList == null || erreurConditionList.isEmpty()) {
			throw new NullPointerException("Le parametre erreurConditionList est vide");
		}

		if (serviceList == null || serviceList.isEmpty()) {
			throw new NullPointerException("Le parametre serviceList est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(disjuctionQuery(erreurConditionList, PersistenceReportingConstantes.ERROR_CONDITION_STRUCTURE));

		builder.must(disjuctionQuery(serviceList, PersistenceReportingConstantes.SERVICE));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	/**
	 * on crée la condition OR avec un queryStringBuilder. L'utilisation de {@link SpanOrQueryBuilder} semble ne pas fonctionner
	 * correctement pour requêter ElasticSearch.
	 *
	 * @param disjunctionValues
	 *            list of enum values. make the enum implements {@link DisjunctionQueryClause} and let {@link
	 *            com.thalesgroup.stif.commons.echange.enums.DisjunctionQueryClause.getName()} return the useful name of the enum value.
	 * @param field
	 *            corresponds to "default_field" in ElasticSearch query. This is the column name in the database, for which
	 *            <code>disjunctionValues</code> will match
	 * @return the query to append to {@link org.elasticsearch.index.query.BoolQueryBuilder.must(QueryBuilder)} (or "must_not", or "should")
	 */
	private QueryBuilder disjuctionQuery(final List<? extends DisjunctionQueryClause> disjunctionValues, final String field) {
		// on créé la la chaine à passer en paramètre, qui sont chaque valeur séparées de "OR"
		final String OR_WORD = " OR ";
		final StringBuilder orQuery = new StringBuilder();
		// "ERR1 OR ERR2 OR ERR3 OR "
		for (int index = 0; index < disjunctionValues.size() - 1; index++) {
			final DisjunctionQueryClause clause = disjunctionValues.get(index);
			orQuery.append(clause.getName() + OR_WORD);
		}

		// "ERR4"
		orQuery.append(disjunctionValues.get(disjunctionValues.size() - 1).getName()); //last index : don't end with "OR"

		final QueryStringQueryBuilder queryStringBuilder = QueryBuilders.queryString(orQuery.toString());
		queryStringBuilder.defaultField(field);

		return queryStringBuilder;
	}

	@Override
	public Page<WSGenericMetierEntity> findArretBySensAndClassName(final String mode, final List<ModuleEnum> composants, final String sens,
			final Date from, final Date to, final String domaine) {

		if (mode == null) {
			throw new NullPointerException("Le parametre mode est null");
		}

		if (composants == null) {
			throw new NullPointerException("Le parametre composant est vide");
		}

		if (Strings.isNullOrEmpty(sens)) {
			throw new NullPointerException("Le parametre sens est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(wildcardQuery(PersistenceReportingConstantes.MODE, "*" + mode + "*"));
		builder.must(wildcardQuery(PersistenceReportingConstantes.DOMAINE, "*" + domaine + "*"));

		for (final ModuleEnum composant : composants) {
			builder.should(wildcardQuery(PersistenceReportingConstantes.CLASSNAME, "*" + composant.name().toLowerCase() + "*"));
		}

		builder.must(termQuery(PersistenceReportingConstantes.SENS, sens));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findLigneBySensAndClassName(final String mode, final List<ModuleEnum> composants, final String sens,
			final Date from, final Date to, final String domaine) {

		if (mode == null) {
			throw new NullPointerException("Le parametre mode est null");
		}

		if (composants == null) {
			throw new NullPointerException("Le parametre composant est vide");
		}

		if (Strings.isNullOrEmpty(sens)) {
			throw new NullPointerException("Le parametre sens est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(wildcardQuery(PersistenceReportingConstantes.MODE, "*" + mode + "*"));
		builder.must(wildcardQuery(PersistenceReportingConstantes.DOMAINE, "*" + domaine + "*"));

		for (final ModuleEnum composant : composants) {
			builder.should(wildcardQuery(PersistenceReportingConstantes.CLASSNAME, "*" + composant.name().toLowerCase() + "*"));
		}

		builder.must(termQuery(PersistenceReportingConstantes.SENS, sens));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSGenericMetierEntity> findByComposantAndSens(final ModeEchange mode, final String composant, final String sens,
			final Date from, final Date to) {

		if (mode == null) {
			throw new NullPointerException("Le parametre mode est null");
		}

		if (composant == null) {
			throw new NullPointerException("Le parametre composant est vide");
		}

		if (Strings.isNullOrEmpty(sens)) {
			throw new NullPointerException("Le parametre sens est vide");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(wildcardQuery(PersistenceReportingConstantes.MODE, "*" + mode.name() + "*"));

		//		for (Modules composant : composants) {
		//			builder.should(wildcardQuery("logs.classname", "*" + composant.name() + "*"));
		//		}

		builder.must(wildcardQuery(PersistenceReportingConstantes.CLASSNAME, "*" + composant.toLowerCase() + "*"));

		builder.must(termQuery(PersistenceReportingConstantes.SENS, sens));

		builder.must(termQuery(PersistenceReportingConstantes.CODE_ERREUR_IVTR, PersistenceReportingConstantes.NULL_VALUE));

		// le monitoring_ref ne doit pas être null
		builder.mustNot(termQuery(PersistenceReportingConstantes.MONITORING_REF, PersistenceReportingConstantes.NULL_VALUE));

		// le producer_ref ne doit pas être null
		builder.mustNot(termQuery(PersistenceReportingConstantes.REQUESTOR_REF, PersistenceReportingConstantes.NULL_VALUE));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.REQUEST_TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSMetierSuccessEntity> page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
		Page<WSMetierErrorEntity> page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);

		Page<WSGenericMetierEntity> page3 = null;

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
			page = template.queryForPage(searchQuery, WSMetierSuccessEntity.class);
			final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>(page.getContent());
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			} else {
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>();
				l2.addAll(l);
				page3 = new PageImpl<WSGenericMetierEntity>(l2);
			}
		}

		else {
			if (page2 != null && page2.hasContent() && page2.getTotalElements() > 0L) {
				searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.REQUEST_TIMESTAMP));
				page2 = template.queryForPage(searchQuery, WSMetierErrorEntity.class);
				final List<WSGenericMetierEntity> l = new ArrayList<WSGenericMetierEntity>();
				final List<WSGenericMetierEntity> l2 = new ArrayList<WSGenericMetierEntity>(page2.getContent());
				l.addAll(l2);
				page3 = new PageImpl<WSGenericMetierEntity>(l);
			}
		}

		if (page3 == null) {
			final SearchQuery searchQuery2 = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE)).build();
			page3 = template.queryForPage(searchQuery2, WSGenericMetierEntity.class);

		}

		return page3;
	}

	@Override
	public Page<WSPerformanceEntity> find(final String composant, final Date from, final Date to) {

		if (composant == null || composant.isEmpty()) {
			throw new NullPointerException("Le parametre composant est null");
		}

		final BoolQueryBuilder builder = boolQuery();

		builder.must(termQuery(PersistenceReportingConstantes.COMPOSANT, composant));

		if (from != null && to != null) {
			builder.must(rangeQuery(PersistenceReportingConstantes.TIMESTAMP).from(from).to(to));
		}

		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(new PageRequest(0, MAX_RESULT_PER_PAGE))
				.withQuery(builder).build();

		Page<WSPerformanceEntity> page = template.queryForPage(searchQuery, WSPerformanceEntity.class);

		if (page != null && page.hasContent() && page.getTotalElements() > 0L) {
			searchQuery.addSort(new Sort(Sort.Direction.ASC, PersistenceReportingConstantes.TIMESTAMP));
			page = template.queryForPage(searchQuery, WSPerformanceEntity.class);
		}

		return page;
	}

}
