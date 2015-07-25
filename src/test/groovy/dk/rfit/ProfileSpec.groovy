package dk.rfit

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Profile)
class ProfileSpec extends Specification {

	void "Both name and active must be non null"() {
		expect:
		new Profile(profileName: name, active: active, description: description).validate() == validates

		where:
		name      | active    | description | validates
		'Name'    | true      | null        | true
		'Name'    | true      | ''          | true
		'Name'    | true      | 'Something' | true
		'Name'    | null      | 'Something' | false
		''        | true      | 'Something' | false
	}
}
