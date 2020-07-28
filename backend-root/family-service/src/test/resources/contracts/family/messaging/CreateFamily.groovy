package contracts.family.messaging

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Sends a positive response for creating new family
```
given:
	
when:
	create new family command received
then:
	we'll send a message with a positive result
```
""")
    label 'family_created'

    input {
        messageFrom("family.command")
        messageBody([
                family_id  : "123",
                family_name: "super family",
                member_id: "456",
                user_id: "789"
        ])
        messageHeaders {
            header("type", "CreateFamilyCommandMessage")
        }
    }

    outputMessage {
        sentTo 'family.command.response'
        body(
                "ok"
        )
        headers {
            messagingContentType(applicationJson())
        }
    }
}