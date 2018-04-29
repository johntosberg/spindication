import com.google.common.io.Resources
import spin.config.SpinConfig
import ratpack.groovy.template.MarkupTemplateModule
import spin.endpoint.SearchEndpoint
import spin.module.SpinModule
import spin.module.SpotifyApiModule

import static ratpack.groovy.Groovy.ratpack

ratpack {

    serverConfig {
        yaml(Resources.asByteSource(Resources.getResource('application.yml')))
        env()
        sysProps()
        require('/spin', SpinConfig)
    }

    bindings {
        module MarkupTemplateModule
        module SpinModule
        module SpotifyApiModule
    }

    handlers {
        get {
            render file("public/index.html")
        }
        prefix('spotify') {
            insert(SearchEndpoint)
        }

    }
}