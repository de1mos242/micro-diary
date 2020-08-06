package contracts.family.rest

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("get family - success result")
            request {
                method GET()
                url $(consumer(regex('/family/' + uuid())), producer('/family/0dc796e6-e86a-4eea-ae52-a8c748ef57f3'))
                headers {
                    header(authorization(), 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c')
                }
            }
            response {
                status OK()
                headers {
                    contentType(applicationJson())
                }
                body(
                        family_id: "0dc796e6-e86a-4eea-ae52-a8c748ef57f3",
                        family_name: "super family",
                        members: [[
                                          user_id: "3f0d8412-e15a-4d6b-bf90-7e83c1e4193e",
                                  ]],
                        babies: [[
                                         baby_id  : "be9b4211-a97e-4481-9829-e1e48101056c",
                                         baby_name: "kovalsky",
                                 ]]
                )
                async()
            }
        },

        Contract.make {
            name("get family - access denied")
            request {
                method GET()
                url $(consumer(regex('/family/' + uuid())), producer('/family/45e8d220-fae8-4e43-bb9b-218ba024a469'))
                headers {
//                    header(authorization(), regex('Bearer [a-zA-Z0-9-._~+/]+\\.[a-zA-Z0-9-._~+/]+\\.[a-zA-Z0-9-._~+/]+'))
                }
            }
            response {
                status FORBIDDEN()
                headers {
                    contentType(applicationJson())
                }
                body(
                        code: "ACCESS_DENIED"
                )
            }
        }
]