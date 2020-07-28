package contracts.family.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url $(consumer(regex('/family/' + uuid())), producer('/family/0dc796e6-e86a-4eea-ae52-a8c748ef57f3'))
        headers {
            header(authorization(), regex('^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$'))
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
}