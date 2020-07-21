package net.de1mos.microdiary.familyservice.domain.models;

import net.de1mos.microdiary.familyservice.domain.commands.CreateNewFamilyCommand;
import net.de1mos.microdiary.familyservice.domain.events.NewFamilyCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FamilyTest {
    private FixtureConfiguration<Family> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Family.class);
    }

    @Test
    public void testCreateNewFamilyCommand() {
        fixture.given()
                .when(new CreateNewFamilyCommand("familyId", "familyName", "memberId", "userId"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new NewFamilyCreatedEvent("familyId", "familyName", "memberId", "userId"))
                .expectState(state -> {
                    assertThat(state.getFamilyId()).isEqualTo("familyId");
                    assertThat(state.getFamilyName()).isEqualTo("familyName");
                    assertThat(state.getMembers()).hasSize(1);
                    FamilyMember member = state.getMembers().get(0);
                    assertThat(member.getMemberId()).isEqualTo("memberId");
                    assertThat(member.getUserId()).isEqualTo("userId");
                });
    }
}