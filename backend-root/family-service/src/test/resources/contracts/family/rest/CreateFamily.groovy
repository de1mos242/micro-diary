package contracts.family.rest

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name "create family - success create"
            request {
                method PUT()
                url $(consumer(regex('/family/' + uuid())), producer('/family/0dc796e6-e86a-4eea-ae52-a8c748ef57f3'))
                body(
                        family_name: "super family"
                )
                headers {
                    contentType(applicationJsonUtf8())
                    header(authorization(), regex('Bearer [a-zA-Z0-9-._~+/]+\\.[a-zA-Z0-9-._~+/]+\\.[a-zA-Z0-9-._~+/]+'))
                }
            }
            response {
                status NO_CONTENT()
                async()
            }
        }
]