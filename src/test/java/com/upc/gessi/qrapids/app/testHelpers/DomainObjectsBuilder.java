package com.upc.gessi.qrapids.app.testHelpers;

import com.upc.gessi.qrapids.app.domain.models.*;
import com.upc.gessi.qrapids.app.domain.repositories.StrategicIndicator.StrategicIndicatorQualityFactorsRepository;
import com.upc.gessi.qrapids.app.presentation.rest.dto.DTODecisionQualityRequirement;
import com.upc.gessi.qrapids.app.presentation.rest.dto.*;
import com.upc.gessi.qrapids.app.presentation.rest.dto.relations.DTORelationsFactor;
import com.upc.gessi.qrapids.app.presentation.rest.dto.relations.DTORelationsMetric;
import com.upc.gessi.qrapids.app.presentation.rest.dto.relations.DTORelationsSI;
import org.springframework.data.util.Pair;
import qr.models.FixedPart;
import qr.models.Form;
import qr.models.QualityRequirementPattern;

import java.time.LocalDate;
import java.util.*;

public class DomainObjectsBuilder {

    private StrategicIndicatorQualityFactorsRepository strategicIndicatorQualityFactorsRepository;

    public Project buildProject() {
        Long projectId = 1L;
        String projectExternalId = "test";
        String projectName = "Test";
        String projectDescription = "Test project";
        String projectBacklogId = "prj-1";
        Project project = new Project(projectExternalId, projectName, projectDescription, null, true);
        project.setId(projectId);
        project.setBacklogId(projectBacklogId);
        return project;
    }

    public Alert buildAlert(Project project) {
        long alertId = 2L;
        String idElement = "id";
        String name = "Duplication";
        AlertType alertType = AlertType.METRIC;
        float value = 0.4f;
        float threshold = 0.5f;
        String category = "category";
        Date date = new Date();
        AlertStatus alertStatus = AlertStatus.NEW;
        Alert alert = new Alert(idElement, name, alertType, value, threshold, category, date, alertStatus, true, project);
        alert.setId(alertId);
        return alert;
    }

    // build Strategic Indicator without weights
    public Strategic_Indicator buildStrategicIndicator (Project project) {
        Long strategicIndicatorId = 1L;
        String strategicIndicatorName = "Product Quality";
        String strategicIndicatorDescription = "Quality of the product built";

        Strategic_Indicator strategicIndicator = new Strategic_Indicator(strategicIndicatorName, strategicIndicatorDescription, null, project);
        strategicIndicator.setId(strategicIndicatorId);

        List<StrategicIndicatorQualityFactors> qualityFactors = new ArrayList<>();

        Long factor1Id = 1L;
        StrategicIndicatorQualityFactors factor1 = new StrategicIndicatorQualityFactors("codequality", -1, strategicIndicator);
        factor1.setId(factor1Id);
        qualityFactors.add(factor1);

        Long factor2Id = 2L;
        StrategicIndicatorQualityFactors factor2 = new StrategicIndicatorQualityFactors( "softwarestability", -1, strategicIndicator);
        factor2.setId(factor2Id);
        qualityFactors.add(factor2);

        Long factor3Id = 3L;
        StrategicIndicatorQualityFactors factor3 = new StrategicIndicatorQualityFactors( "testingstatus", -1, strategicIndicator);
        factor3.setId(factor3Id);
        qualityFactors.add(factor3);

        strategicIndicator.setQuality_factors(qualityFactors);
        strategicIndicator.setWeighted(false);

        return strategicIndicator;
    }

    public Strategic_Indicator addFactorToStrategicIndicator (Strategic_Indicator si, String factorId, float weight) {
        List<StrategicIndicatorQualityFactors> qualityFactors = new ArrayList<>();

        Long factor1Id = 1L;
        StrategicIndicatorQualityFactors factor1 = new StrategicIndicatorQualityFactors("codequality", -1, si);
        factor1.setId(factor1Id);
        qualityFactors.add(factor1);

        Long factor2Id = 2L;
        StrategicIndicatorQualityFactors factor2 = new StrategicIndicatorQualityFactors( "softwarestability", -1, si);
        factor2.setId(factor2Id);
        qualityFactors.add(factor2);

        Long factor3Id = 3L;
        StrategicIndicatorQualityFactors factor3 = new StrategicIndicatorQualityFactors( "testingstatus", -1, si);
        factor3.setId(factor3Id);
        qualityFactors.add(factor3);

        Long factor4Id = 4L;
        StrategicIndicatorQualityFactors factor4 = new StrategicIndicatorQualityFactors(factorId, weight, si);
        factor4.setId(factor4Id);
        qualityFactors.add(factor4);

        si.setQuality_factors(qualityFactors);
        return si;
    }

    public List<StrategicIndicatorQualityFactors> buildQualityFactors (Strategic_Indicator strategicIndicator) {
        List<StrategicIndicatorQualityFactors> qualityFactors = new ArrayList<>();

        Long factor1Id = 1L;
        StrategicIndicatorQualityFactors factor1 = new StrategicIndicatorQualityFactors("codequality", -1, strategicIndicator);
        factor1.setId(factor1Id);
        qualityFactors.add(factor1);

        Long factor2Id = 2L;
        StrategicIndicatorQualityFactors factor2 = new StrategicIndicatorQualityFactors( "softwarestability", -1, strategicIndicator);
        factor2.setId(factor2Id);
        qualityFactors.add(factor2);

        Long factor3Id = 3L;
        StrategicIndicatorQualityFactors factor3 = new StrategicIndicatorQualityFactors( "testingstatus", -1, strategicIndicator);
        factor3.setId(factor3Id);
        qualityFactors.add(factor3);

        return qualityFactors;
    }

    public DTOStrategicIndicatorEvaluation buildDtoStrategicIndicatorEvaluation (Strategic_Indicator strategicIndicator) {
        List<DTOSIAssessment> dtoSIAssessmentList = new ArrayList<>();

        Long assessment1Id = 10L;
        String assessment1Label = "Good";
        Float assessment1Value = null;
        String assessment1Color = "#00ff00";
        Float assessment1UpperThreshold = 0.66f;
        DTOSIAssessment dtoSIAssessment1 = new DTOSIAssessment(assessment1Id, assessment1Label, assessment1Value, assessment1Color, assessment1UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment1);

        Long assessment2Id = 11L;
        String assessment2Label = "Neutral";
        Float assessment2Value = null;
        String assessment2Color = "#ff8000";
        Float assessment2UpperThreshold = 0.33f;
        DTOSIAssessment dtoSIAssessment2 = new DTOSIAssessment(assessment2Id, assessment2Label, assessment2Value, assessment2Color, assessment2UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment2);

        Long assessment3Id = 11L;
        String assessment3Label = "Bad";
        Float assessment3Value = null;
        String assessment3Color = "#ff0000";
        Float assessment3UpperThreshold = 0f;
        DTOSIAssessment dtoSIAssessment3 = new DTOSIAssessment(assessment3Id, assessment3Label, assessment3Value, assessment3Color, assessment3UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment3);

        Float strategicIndicatorValue = 0.7f;
        String strategicIndicatorCategory = "Good";
        Pair<Float, String> strategicIndicatorValuePair = Pair.of(strategicIndicatorValue, strategicIndicatorCategory);
        String datasource = "Q-Rapdis Dashboard";
        String categoriesDescription = "[Good (0,67), Neutral (0,33), Bad (0,00)]";
        String strategicIndicatorRationale = "factors: {...}, formula: ..., value: ..., category: ...";
        DTOStrategicIndicatorEvaluation dtoStrategicIndicatorEvaluation = new DTOStrategicIndicatorEvaluation(strategicIndicator.getExternalId(), strategicIndicator.getName(), strategicIndicator.getDescription(), strategicIndicatorValuePair, strategicIndicatorRationale, dtoSIAssessmentList, LocalDate.now(), datasource, strategicIndicator.getId(), categoriesDescription, false);
        dtoStrategicIndicatorEvaluation.setHasFeedback(false);
        dtoStrategicIndicatorEvaluation.setForecastingError(null);

        return dtoStrategicIndicatorEvaluation;
    }

    public QualityRequirementPattern buildQualityRequirementPattern () {
        String formText = "The ratio of files without duplications should be at least %value%";
        FixedPart fixedPart = new FixedPart(formText);
        String formName = "Duplications";
        String formDescription = "The ratio of files without duplications should be at least the given value";
        String formComments = "No comments";
        Form form = new Form(formName, formDescription, formComments, fixedPart);
        List<Form> formList = new ArrayList<>();
        formList.add(form);
        Integer requirementId = 100;
        String requirementName = "Duplications";
        String requirementComments = "No comments";
        String requirementDescription = "No description";
        String requirementGoal = "Improve the quality of the source code";
        String requirementCostFunction = "No cost function";
        QualityRequirementPattern qualityRequirementPattern = new QualityRequirementPattern(requirementId, requirementName, requirementComments, requirementDescription, requirementGoal, formList, requirementCostFunction);

        return qualityRequirementPattern;
    }

    public Decision buildDecision (Project project, DecisionType type) {
        Long decisionId = 2L;
        DecisionType decisionType = type;
        Date date = new Date();
        String rationale = "User comments";
        int patternId = 100;
        Decision decision = new Decision(decisionType, date, null, rationale, patternId, project);
        decision.setId(decisionId);
        return decision;
    }

    public QualityRequirement buildQualityRequirement (Alert alert, Decision decision, Project project) {
        Long requirementId = 3L;
        String requirement = "The ratio of files without duplications should be at least 0.8";
        String description = "The ratio of files without duplications should be at least the given value";
        String goal = "Improve the quality of the source code";
        String qrBacklogUrl =  "https://backlog.example/issue/999";
        String qrBacklogId = "ID-999";
        QualityRequirement qualityRequirement = new QualityRequirement(requirement, description, goal, alert, decision, project);
        qualityRequirement.setId(requirementId);
        qualityRequirement.setBacklogUrl(qrBacklogUrl);
        qualityRequirement.setBacklogId(qrBacklogId);
        return qualityRequirement;
    }

    public DTODecisionQualityRequirement buildDecisionWithQualityRequirement (QualityRequirement qualityRequirement) {
        return new DTODecisionQualityRequirement(qualityRequirement.getDecision().getId(),
                qualityRequirement.getDecision().getType(),
                qualityRequirement.getDecision().getDate(),
                null,
                qualityRequirement.getDecision().getRationale(),
                qualityRequirement.getDecision().getPatternId(),
                qualityRequirement.getRequirement(),
                qualityRequirement.getDescription(),
                qualityRequirement.getGoal(),
                qualityRequirement.getBacklogId(),
                qualityRequirement.getBacklogUrl());
    }

    public DTODecisionQualityRequirement buildDecisionWithoutQualityRequirement (Decision decision) {
        return new DTODecisionQualityRequirement(decision.getId(),
                decision.getType(),
                decision.getDate(),
                null,
                decision.getRationale(),
                decision.getPatternId(),
                null,
                null,
                null,
                null,
                null);
    }

    public DTOQualityFactor buildDTOQualityFactor () {
        String metricId = "fasttests";
        String metricName = "Fast Tests";
        String metricDescription = "Percentage of tests under the testing duration threshold";
        float metricValue = 0.8f;
        LocalDate evaluationDate = LocalDate.now();
        String metricRationale = "parameters: {...}, formula: ...";
        DTOMetric dtoMetric = new DTOMetric(metricId, metricName, metricDescription, null, metricRationale, evaluationDate, metricValue);
        List<DTOMetric> dtoMetricList = new ArrayList<>();
        dtoMetricList.add(dtoMetric);

        String factorId = "testingperformance";
        String factorName = "Testing Performance";
        return new DTOQualityFactor(factorId, factorName, dtoMetricList);
    }

    public DTOQualityFactor buildDTOQualityFactorForPrediction () {
        String metricId = "fasttests";
        String metricName = "Fast Tests";
        String metricDescription = "Percentage of tests under the testing duration threshold";
        String metricDataSource = "Forecast";
        Double metricValue = 0.8;
        LocalDate evaluationDate = LocalDate.now();
        String metricRationale = "Forecast";
        DTOMetric dtoMetric = new DTOMetric(metricId, metricName, metricDescription, metricDataSource, metricRationale, evaluationDate, metricValue.floatValue());
        Double first80 = 0.97473043;
        Double second80 = 0.9745246;
        Pair<Float, Float> confidence80 = Pair.of(first80.floatValue(), second80.floatValue());
        dtoMetric.setConfidence80(confidence80);
        Double first95 = 0.9747849;
        Double second95 = 0.97447014;
        Pair<Float, Float> confidence95 = Pair.of(first95.floatValue(), second95.floatValue());
        dtoMetric.setConfidence95(confidence95);
        List<DTOMetric> dtoMetricList = new ArrayList<>();
        dtoMetricList.add(dtoMetric);

        String factorId = "testingperformance";
        String factorName = "Testing Performance";
        return new DTOQualityFactor(factorId, factorName, dtoMetricList);
    }

    public DTOFactor buildDTOFactor () {
        String factorId = "testingperformance";
        String factorName = "Testing Performance";
        String factorDescription = "Performance of the tests";
        float factorValue = 0.8f;
        LocalDate evaluationDate = LocalDate.now();
        String factorRationale = "parameters: {...}, formula: ...";
        String strategicIndicator = "processperformance";
        List<String> strategicIndicatorsList = new ArrayList<>();
        strategicIndicatorsList.add(strategicIndicator);
        return new DTOFactor(factorId, factorName, factorDescription, factorValue, evaluationDate, null, factorRationale, strategicIndicatorsList);
    }

    public DTOMetric buildDTOMetric () {
        String metricId = "fasttests";
        String metricName = "Fast Tests";
        String metricDescription = "Percentage of tests under the testing duration threshold";
        float metricValue = 0.8f;
        LocalDate evaluationDate = LocalDate.now();
        String metricRationale = "parameters: {...}, formula: ...";
        return new DTOMetric(metricId, metricName, metricDescription, null, metricRationale, evaluationDate, metricValue);
    }

    public DTOStrategicIndicatorEvaluation buildDTOStrategicIndicatorEvaluation () {
        List<DTOSIAssessment> dtoSIAssessmentList = new ArrayList<>();

        Long assessment1Id = 10L;
        String assessment1Label = "Good";
        Float assessment1Value = null;
        String assessment1Color = "#00ff00";
        Float assessment1UpperThreshold = 0.66f;
        DTOSIAssessment dtoSIAssessment1 = new DTOSIAssessment(assessment1Id, assessment1Label, assessment1Value, assessment1Color, assessment1UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment1);

        Long assessment2Id = 11L;
        String assessment2Label = "Neutral";
        Float assessment2Value = null;
        String assessment2Color = "#ff8000";
        Float assessment2UpperThreshold = 0.33f;
        DTOSIAssessment dtoSIAssessment2 = new DTOSIAssessment(assessment2Id, assessment2Label, assessment2Value, assessment2Color, assessment2UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment2);

        Long assessment3Id = 11L;
        String assessment3Label = "Bad";
        Float assessment3Value = null;
        String assessment3Color = "#ff0000";
        Float assessment3UpperThreshold = 0f;
        DTOSIAssessment dtoSIAssessment3 = new DTOSIAssessment(assessment3Id, assessment3Label, assessment3Value, assessment3Color, assessment3UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment3);

        String strategicIndicatorId = "processperformance";
        Long strategicIndicatorDbId = 1L;
        String strategicIndicatorName = "Process Performance";
        String strategicIndicatorDescription = "Performance of the processes involved in the development";
        Float strategicIndicatorValue = 0.8f;
        String strategicIndicatorCategory = "Good";
        Pair<Float, String> strategicIndicatorValuePair = Pair.of(strategicIndicatorValue, strategicIndicatorCategory);
        String dateString = "2019-07-07";
        LocalDate date = LocalDate.parse(dateString);
        String datasource = "Q-Rapdis Dashboard";
        String categoriesDescription = "[Good (0,67), Neutral (0,33), Bad (0,00)]";
        String strategicIndicatorRationale = "factors: {...}, formula: ..., value: ..., category: ...";
        DTOStrategicIndicatorEvaluation dtoStrategicIndicatorEvaluation = new DTOStrategicIndicatorEvaluation(strategicIndicatorId, strategicIndicatorName, strategicIndicatorDescription, strategicIndicatorValuePair, strategicIndicatorRationale, dtoSIAssessmentList, date, datasource, strategicIndicatorDbId, categoriesDescription, false);
        dtoStrategicIndicatorEvaluation.setHasFeedback(false);
        dtoStrategicIndicatorEvaluation.setForecastingError(null);
        return dtoStrategicIndicatorEvaluation;
    }

    public List<SICategory> buildSICategoryList () {
        Long strategicIndicatorGoodCategoryId = 10L;
        String strategicIndicatorGoodCategoryName = "Good";
        String strategicIndicatorGoodCategoryColor = "#00ff00";
        SICategory siGoodCategory = new SICategory(strategicIndicatorGoodCategoryName, strategicIndicatorGoodCategoryColor);
        siGoodCategory.setId(strategicIndicatorGoodCategoryId);

        Long strategicIndicatorNeutralCategoryId = 11L;
        String strategicIndicatorNeutralCategoryName = "Neutral";
        String strategicIndicatorNeutralCategoryColor = "#ff8000";
        SICategory siNeutralCategory = new SICategory(strategicIndicatorNeutralCategoryName, strategicIndicatorNeutralCategoryColor);
        siNeutralCategory.setId(strategicIndicatorNeutralCategoryId);

        Long strategicIndicatorBadCategoryId = 12L;
        String strategicIndicatorBadCategoryName = "Bad";
        String strategicIndicatorBadCategoryColor = "#ff0000";
        SICategory siBadCategory = new SICategory(strategicIndicatorBadCategoryName, strategicIndicatorBadCategoryColor);
        siBadCategory.setId(strategicIndicatorBadCategoryId);

        List<SICategory> siCategoryList = new ArrayList<>();
        siCategoryList.add(siGoodCategory);
        siCategoryList.add(siNeutralCategory);
        siCategoryList.add(siBadCategory);

        return siCategoryList;
    }

    public List<Map<String, String>> buildRawSICategoryList () {
        String strategicIndicatorGoodCategoryName = "Good";
        String strategicIndicatorGoodCategoryColor = "#00ff00";
        Map<String, String> strategicIndicatorGoodCategory = new HashMap<>();
        strategicIndicatorGoodCategory.put("name", strategicIndicatorGoodCategoryName);
        strategicIndicatorGoodCategory.put("color", strategicIndicatorGoodCategoryColor);

        String strategicIndicatorNeutralCategoryName = "Neutral";
        String strategicIndicatorNeutralCategoryColor = "#ff8000";
        Map<String, String> strategicIndicatorNeutralCategory = new HashMap<>();
        strategicIndicatorNeutralCategory.put("name", strategicIndicatorNeutralCategoryName);
        strategicIndicatorNeutralCategory.put("color", strategicIndicatorNeutralCategoryColor);

        String strategicIndicatorBadCategoryName = "Bad";
        String strategicIndicatorBadCategoryColor = "#ff0000";
        Map<String, String> strategicIndicatorBadCategory = new HashMap<>();
        strategicIndicatorBadCategory.put("name", strategicIndicatorBadCategoryName);
        strategicIndicatorBadCategory.put("color", strategicIndicatorBadCategoryColor);

        List<Map<String, String>> strategicIndicatorCategoriesList = new ArrayList<>();
        strategicIndicatorCategoriesList.add(strategicIndicatorGoodCategory);
        strategicIndicatorCategoriesList.add(strategicIndicatorNeutralCategory);
        strategicIndicatorCategoriesList.add(strategicIndicatorBadCategory);

        return strategicIndicatorCategoriesList;
    }

    public List<QFCategory> buildFactorCategoryList () {
        Long factorGoodCategoryId = 10L;
        String factorGoodCategoryName = "Good";
        String factorGoodCategoryColor = "#00ff00";
        float factorGoodCategoryUpperThreshold = 1f;
        QFCategory factorGoodCategory = new QFCategory(factorGoodCategoryName, factorGoodCategoryColor, factorGoodCategoryUpperThreshold);
        factorGoodCategory.setId(factorGoodCategoryId);

        Long factorNeutralCategoryId = 11L;
        String factorNeutralCategoryName = "Neutral";
        String factorNeutralCategoryColor = "#ff8000";
        float factorNeutralCategoryUpperThreshold = 0.67f;
        QFCategory factorNeutralCategory = new QFCategory(factorNeutralCategoryName, factorNeutralCategoryColor, factorNeutralCategoryUpperThreshold);
        factorNeutralCategory.setId(factorNeutralCategoryId);

        Long factorBadCategoryId = 12L;
        String factorBadCategoryName = "Bad";
        String factorBadCategoryColor = "#ff0000";
        float factorBadCategoryUpperThreshold = 0.33f;
        QFCategory factorBadCategory = new QFCategory(factorBadCategoryName, factorBadCategoryColor, factorBadCategoryUpperThreshold);
        factorBadCategory.setId(factorBadCategoryId);

        List<QFCategory> factorCategoryList = new ArrayList<>();
        factorCategoryList.add(factorGoodCategory);
        factorCategoryList.add(factorNeutralCategory);
        factorCategoryList.add(factorBadCategory);

        return factorCategoryList;
    }

    public List<Map<String,String>> buildRawFactorCategoryList () {
        String factorGoodCategoryName = "Good";
        String factorGoodCategoryColor = "#00ff00";
        float factorGoodCategoryUpperThreshold = 1.0f;
        Map<String, String> factorGoodCategory = new HashMap<>();
        factorGoodCategory.put("name", factorGoodCategoryName);
        factorGoodCategory.put("color", factorGoodCategoryColor);
        factorGoodCategory.put("upperThreshold", Float.toString(factorGoodCategoryUpperThreshold));

        String factorNeutralCategoryName = "Neutral";
        String factorNeutralCategoryColor = "#ff8000";
        float factorNeutralCategoryUpperThreshold = 0.67f;
        Map<String, String> factorNeutralCategory = new HashMap<>();
        factorNeutralCategory.put("name", factorNeutralCategoryName);
        factorNeutralCategory.put("color", factorNeutralCategoryColor);
        factorNeutralCategory.put("upperThreshold", Float.toString(factorNeutralCategoryUpperThreshold));

        String factorBadCategoryName = "Bad";
        String factorBadCategoryColor = "#ff0000";
        float factorBadCategoryUpperThreshold = 0.33f;
        Map<String, String> factorBadCategory = new HashMap<>();
        factorBadCategory.put("name", factorBadCategoryName);
        factorBadCategory.put("color", factorBadCategoryColor);
        factorBadCategory.put("upperThreshold", Float.toString(factorBadCategoryUpperThreshold));

        List<Map<String, String>> factorCategoriesList = new ArrayList<>();
        factorCategoriesList.add(factorGoodCategory);
        factorCategoriesList.add(factorNeutralCategory);
        factorCategoriesList.add(factorBadCategory);

        return factorCategoriesList;
    }

    public List<MetricCategory> buildMetricCategoryList () {
        Long metricGoodCategoryId = 10L;
        String metricGoodCategoryName = "Good";
        String metricGoodCategoryColor = "#00ff00";
        float metricGoodCategoryUpperThreshold = 1f;
        MetricCategory metricGoodCategory = new MetricCategory(metricGoodCategoryName, metricGoodCategoryColor, metricGoodCategoryUpperThreshold);
        metricGoodCategory.setId(metricGoodCategoryId);

        Long metricNeutralCategoryId = 11L;
        String metricNeutralCategoryName = "Neutral";
        String metricNeutralCategoryColor = "#ff8000";
        float metricNeutralCategoryUpperThreshold = 0.67f;
        MetricCategory metricNeutralCategory = new MetricCategory(metricNeutralCategoryName, metricNeutralCategoryColor, metricNeutralCategoryUpperThreshold);
        metricNeutralCategory.setId(metricNeutralCategoryId);

        Long metricBadCategoryId = 12L;
        String metricBadCategoryName = "Bad";
        String metricBadCategoryColor = "#ff0000";
        float metricBadCategoryUpperThreshold = 0.33f;
        MetricCategory metricBadCategory = new MetricCategory(metricBadCategoryName, metricBadCategoryColor, metricBadCategoryUpperThreshold);
        metricBadCategory.setId(metricBadCategoryId);

        List<MetricCategory> metricCategoryList = new ArrayList<>();
        metricCategoryList.add(metricGoodCategory);
        metricCategoryList.add(metricNeutralCategory);
        metricCategoryList.add(metricBadCategory);

        return metricCategoryList;
    }

    public List<Map<String, String>> buildRawMetricCategoryList () {
        String metricGoodCategoryName = "Good";
        String metricGoodCategoryColor = "#00ff00";
        float metricGoodCategoryUpperThreshold = 1.0f;
        Map<String, String> metricGoodCategory = new HashMap<>();
        metricGoodCategory.put("name", metricGoodCategoryName);
        metricGoodCategory.put("color", metricGoodCategoryColor);
        metricGoodCategory.put("upperThreshold", Float.toString(metricGoodCategoryUpperThreshold));

        String metricNeutralCategoryName = "Neutral";
        String metricNeutralCategoryColor = "#ff8000";
        float metricNeutralCategoryUpperThreshold = 0.67f;
        Map<String, String> metricNeutralCategory = new HashMap<>();
        metricNeutralCategory.put("name", metricNeutralCategoryName);
        metricNeutralCategory.put("color", metricNeutralCategoryColor);
        metricNeutralCategory.put("upperThreshold", Float.toString(metricNeutralCategoryUpperThreshold));

        String metricBadCategoryName = "Bad";
        String metricBadCategoryColor = "#ff0000";
        float metricBadCategoryUpperThreshold = 0.33f;
        Map<String, String> metricBadCategory = new HashMap<>();
        metricBadCategory.put("name", metricBadCategoryName);
        metricBadCategory.put("color", metricBadCategoryColor);
        metricBadCategory.put("upperThreshold", Float.toString(metricBadCategoryUpperThreshold));

        List<Map<String, String>> metricCategoriesList = new ArrayList<>();
        metricCategoriesList.add(metricGoodCategory);
        metricCategoriesList.add(metricNeutralCategory);
        metricCategoriesList.add(metricBadCategory);

        return metricCategoriesList;
    }

    public List<DTOSIAssessment> buildDTOSIAssessmentList () {
        List<DTOSIAssessment> dtoSIAssessmentList = new ArrayList<>();

        Long assessment1Id = 10L;
        String assessment1Label = "Good";
        Float assessment1Value = 0.5f;
        String assessment1Color = "#00ff00";
        Float assessment1UpperThreshold = 0.66f;
        DTOSIAssessment dtoSIAssessment1 = new DTOSIAssessment(assessment1Id, assessment1Label, assessment1Value, assessment1Color, assessment1UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment1);

        Long assessment2Id = 11L;
        String assessment2Label = "Neutral";
        Float assessment2Value = 0.3f;
        String assessment2Color = "#ff8000";
        Float assessment2UpperThreshold = 0.33f;
        DTOSIAssessment dtoSIAssessment2 = new DTOSIAssessment(assessment2Id, assessment2Label, assessment2Value, assessment2Color, assessment2UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment2);

        Long assessment3Id = 11L;
        String assessment3Label = "Bad";
        Float assessment3Value = 0.2f;
        String assessment3Color = "#ff0000";
        Float assessment3UpperThreshold = 0f;
        DTOSIAssessment dtoSIAssessment3 = new DTOSIAssessment(assessment3Id, assessment3Label, assessment3Value, assessment3Color, assessment3UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment3);

        return dtoSIAssessmentList;
    }

    public List<DTORelationsSI> buildDTORelationsSI () {
        String metricId = "nonblockingfiles";
        String metricValue = "0.8";
        String metricWeight = "1";
        DTORelationsMetric dtoRelationsMetric = new DTORelationsMetric(metricId);
        dtoRelationsMetric.setWeightedValue(metricValue);
        dtoRelationsMetric.setWeight(metricWeight);
        List<DTORelationsMetric> dtoRelationsMetricList = new ArrayList<>();
        dtoRelationsMetricList.add(dtoRelationsMetric);

        String factorId = "blockingcode";
        String factorValue = "0.8";
        String factorWeight = "1";
        DTORelationsFactor dtoRelationsFactor = new DTORelationsFactor(factorId);
        dtoRelationsFactor.setWeightedValue(factorValue);
        dtoRelationsFactor.setWeight(factorWeight);
        dtoRelationsFactor.setMetrics(dtoRelationsMetricList);
        List<DTORelationsFactor> dtoRelationsFactorList = new ArrayList<>();
        dtoRelationsFactorList.add(dtoRelationsFactor);

        String strategicIndicatorId = "blocking";
        String strategicIndicatorValue = "0.8";
        String strategicIndicatorValueDescription = "Good (0.8)";
        String strategicIndicatorColor = "#00ff00";
        DTORelationsSI dtoRelationsSI = new DTORelationsSI(strategicIndicatorId);
        dtoRelationsSI.setValue(strategicIndicatorValue);
        dtoRelationsSI.setValueDescription(strategicIndicatorValueDescription);
        dtoRelationsSI.setColor(strategicIndicatorColor);
        dtoRelationsSI.setFactors(dtoRelationsFactorList);
        List<DTORelationsSI> dtoRelationsSIList = new ArrayList<>();
        dtoRelationsSIList.add(dtoRelationsSI);

        return dtoRelationsSIList;
    }

    public List<DTOMilestone> buildDTOMilestoneList () {
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        String milestoneName = "Version 1.3";
        String milestoneDescription = "Version 1.3 adding new features";
        String milestoneType = "Release";
        List<DTOMilestone> milestoneList = new ArrayList<>();
        milestoneList.add(new DTOMilestone(date.toString(), milestoneName, milestoneDescription, milestoneType));
        return milestoneList;
    }

    public List<DTOPhase> buildDTOPhaseList () {
        LocalDate dateFrom = LocalDate.now().minusDays(15);
        LocalDate dateTo = LocalDate.now().plusDays(15);
        String phaseName = "Development";
        String phaseDescription = "Implementation of project functionalities";
        List<DTOPhase> phaseList = new ArrayList<>();
        phaseList.add(new DTOPhase(dateFrom.toString(), phaseName, phaseDescription, dateTo.toString()));
        return phaseList;
    }

    public DTOSICurrentHistoricEvaluation buildDTOSICurrentHistoricEvaluation() {
        List<DTOSIAssessment> dtoSIAssessmentList = new ArrayList<>();

        Long assessment1Id = 10L;
        String assessment1Label = "Good";
        Float assessment1Value = null;
        String assessment1Color = "#00ff00";
        Float assessment1UpperThreshold = 0.66f;
        DTOSIAssessment dtoSIAssessment1 = new DTOSIAssessment(assessment1Id, assessment1Label, assessment1Value, assessment1Color, assessment1UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment1);

        Long assessment2Id = 11L;
        String assessment2Label = "Neutral";
        Float assessment2Value = null;
        String assessment2Color = "#ff8000";
        Float assessment2UpperThreshold = 0.33f;
        DTOSIAssessment dtoSIAssessment2 = new DTOSIAssessment(assessment2Id, assessment2Label, assessment2Value, assessment2Color, assessment2UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment2);

        Long assessment3Id = 11L;
        String assessment3Label = "Bad";
        Float assessment3Value = null;
        String assessment3Color = "#ff0000";
        Float assessment3UpperThreshold = 0f;
        DTOSIAssessment dtoSIAssessment3 = new DTOSIAssessment(assessment3Id, assessment3Label, assessment3Value, assessment3Color, assessment3UpperThreshold);
        dtoSIAssessmentList.add(dtoSIAssessment3);

        String strategicIndicatorId = "processperformance";
        Long strategicIndicatorDbId = 1L;
        String strategicIndicatorName = "Process Performance";
        String strategicIndicatorDescription = "Performance of the processes involved in the development";
        Float strategicIndicatorValue = 0.8f;
        String strategicIndicatorCategory = "Good";
        Pair<Float, String> strategicIndicatorValuePair = Pair.of(strategicIndicatorValue, strategicIndicatorCategory);
        String dateString = "2019-07-07";
        LocalDate date = LocalDate.parse(dateString);
        String strategicIndicatorRationale = "factors: {...}, formula: ..., value: ..., category: ...";
        DTOSICurrentHistoricEvaluation dtoSICurrentHistoricEvaluationEvaluation = new DTOSICurrentHistoricEvaluation(strategicIndicatorId,"Test", strategicIndicatorName, strategicIndicatorDescription, strategicIndicatorValuePair, strategicIndicatorDbId, strategicIndicatorRationale, dtoSIAssessmentList, date);


        return dtoSICurrentHistoricEvaluationEvaluation;

    }

    public DTOSICurrentHistoricEvaluation.DTOHistoricalData buildDTOHistoricalData() {
        Float strategicIndicatorValue = 0.8f;
        String strategicIndicatorCategory = "Good";
        Pair<Float, String> strategicIndicatorValuePair = Pair.of(strategicIndicatorValue, strategicIndicatorCategory);
        String dateString = "2019-07-07";
        LocalDate date = LocalDate.parse(dateString);
        String strategicIndicatorRationale = "factors: {...}, formula: ..., value: ..., category: ...";
        DTOSICurrentHistoricEvaluation.DTOHistoricalData dtoHistoricalData = new DTOSICurrentHistoricEvaluation.DTOHistoricalData(strategicIndicatorValuePair,strategicIndicatorRationale,date);
        return dtoHistoricalData;
    }
}
