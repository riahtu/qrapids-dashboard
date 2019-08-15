package com.upc.gessi.qrapids.app.domain.controllers;

import com.upc.gessi.qrapids.app.domain.models.*;
import com.upc.gessi.qrapids.app.domain.repositories.Alert.AlertRepository;
import com.upc.gessi.qrapids.app.domain.repositories.Decision.DecisionRepository;
import com.upc.gessi.qrapids.app.domain.repositories.QR.QRRepository;
import com.upc.gessi.qrapids.app.testHelpers.DomainObjectsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QualityRequirementControllerTest {

    private DomainObjectsBuilder domainObjectsBuilder;

    @Mock
    private QRRepository qrRepository;

    @Mock
    private DecisionRepository decisionRepository;

    @Mock
    private AlertRepository alertRepository;

    @InjectMocks
    private QualityRequirementController qualityRequirementController;

    @Before
    public void setUp() {
        domainObjectsBuilder = new DomainObjectsBuilder();
    }

    @Test
    public void getQualityRequirementForDecision() {
        // Given
        Project project = domainObjectsBuilder.buildProject();
        Alert alert = domainObjectsBuilder.buildAlert(project);
        Decision decision = domainObjectsBuilder.buildDecision(project, DecisionType.ADD);
        alert.setDecision(decision);
        QualityRequirement qualityRequirement = domainObjectsBuilder.buildQualityRequirement(alert, decision);
        when(qrRepository.findByDecisionId(decision.getId())).thenReturn(qualityRequirement);

        // When
        QualityRequirement qualityRequirementFound = qualityRequirementController.getQualityRequirementForDecision(decision);

        // Then
        assertEquals(qualityRequirement, qualityRequirementFound);
        verify(qrRepository, times(1)).findByDecisionId(decision.getId());
    }

    @Test
    public void ignoreQualityRequirement() {
        // Given
        Project project = domainObjectsBuilder.buildProject();
        String rationale = "Very important";
        int patternId = 100;

        // When
        qualityRequirementController.ignoreQualityRequirement(project, rationale, patternId);

        // Then
        ArgumentCaptor<Decision> decisionArgumentCaptor = ArgumentCaptor.forClass(Decision.class);
        verify(decisionRepository, times(1)).save(decisionArgumentCaptor.capture());
        Decision decisionSaved = decisionArgumentCaptor.getValue();
        assertEquals(DecisionType.IGNORE, decisionSaved.getType());
        assertEquals(rationale, decisionSaved.getRationale());
        assertEquals(patternId, decisionSaved.getPatternId());

        verifyZeroInteractions(alertRepository);
    }

    @Test
    public void ignoreQualityRequirementForAlert() {
        // Given
        Project project = domainObjectsBuilder.buildProject();
        Alert alert = domainObjectsBuilder.buildAlert(project);
        String rationale = "Very important";
        int patternId = 100;
        when(decisionRepository.save(any(Decision.class))).then(returnsFirstArg());

        // When
        qualityRequirementController.ignoreQualityRequirementForAlert(project, alert, rationale, patternId);

        // Then
        ArgumentCaptor<Decision> decisionArgumentCaptor = ArgumentCaptor.forClass(Decision.class);
        verify(decisionRepository, times(1)).save(decisionArgumentCaptor.capture());
        Decision decisionSaved = decisionArgumentCaptor.getValue();
        assertEquals(DecisionType.IGNORE, decisionSaved.getType());
        assertEquals(rationale, decisionSaved.getRationale());
        assertEquals(patternId, decisionSaved.getPatternId());

        ArgumentCaptor<Alert> alertArgumentCaptor = ArgumentCaptor.forClass(Alert.class);
        verify(alertRepository, times(1)).save(alertArgumentCaptor.capture());
        Alert alertSaved = alertArgumentCaptor.getValue();
        assertEquals(AlertStatus.RESOLVED, alertSaved.getStatus());
        assertEquals(decisionSaved, alertSaved.getDecision());
    }
}