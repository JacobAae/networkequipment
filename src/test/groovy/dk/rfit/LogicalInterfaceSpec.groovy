package dk.rfit

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LogicalInterface)
class LogicalInterfaceSpec extends Specification {

    void "Both name and active must be non null"() {
        expect:
		new LogicalInterface(name: name, active: active).validate() == validates

	    where:
	    name                | active    | validates
	    'Interface name'    | true      | true
	    'Interface name'    | null      | false
	    ''                  | true      | false
    }
}
