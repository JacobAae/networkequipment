package dk.rfit

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(NetworkDevice)
class NetworkDeviceSpec extends Specification {

    void "Test constraints"() {
        expect:
        new NetworkDevice(label: label, location: location, deviceType: deviceType, comments: comments).validate() == validate

	    where:
	    label       | location      | deviceType        | comments          | validate
	    'C05'       | 'Krydsfelt'   | DeviceType.MX80   | null              | true
	    'C05'       | 'Krydsfelt'   | DeviceType.MX80   | ''                | true
	    'C05'       | 'Krydsfelt'   | DeviceType.MX80   | 'Kommentar'       | true
	    ''          | 'Krydsfelt'   | DeviceType.MX80   | 'Kommentar'       | false
	    'C05'       | ''            | DeviceType.MX80   | 'Kommentar'       | false
	    'C05'       | 'Krydsfelt'   | null              | 'Kommentar'       | false
    }
}
