import domain.Track
import org.grails.datastore.gorm.neo4j.Neo4jDatastore
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {

  Neo4jDatastore datastore = new Neo4jDatastore(['grails.neo4j.url':'bolt://localhost:7687'] as Map, Track)

  bindings {
    module MarkupTemplateModule
  }

  handlers {
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { dir "public" }
  }
}
