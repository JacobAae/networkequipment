package dk.rfit

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Port)
@Mock([NetworkDevice])
class PortSpec extends Specification {

	@Shared
	NetworkDevice networkDevice

    def setup() {
	    networkDevice = new NetworkDevice(label: 'Demo device', location: 'Location', deviceType: DeviceType.ACX1100_AC)
	    networkDevice.save(failOnError: true)

    }

    void "Test getValidPort is in fact valid"() {
        expect:
            getValidPort().validate()
    }

	void "Port must be between 0 and 47"() {
		setup:
		Port port = validPort

		when:
		port.portNumber = portNumber

		then:
		port.validate() == validate

		where:
		portNumber  | validate
		-1          | false
		0           | true
		47          | true
		48          | false
	}

	private Port getValidPort() {
		new Port(
				used: true,
				xpc: 0,
				mic: 0,
				portNumber: 0,
				physicalInterface: PhysicalInterface.T,
				profile: new Profile(),
				logicalInterface: new LogicalInterface(),
				networkdevice: networkDevice
		)

	}

}
