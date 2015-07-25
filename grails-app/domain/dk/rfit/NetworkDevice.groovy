package dk.rfit

class NetworkDevice {

	String label
	String location
	DeviceType deviceType
	String comments

	static hasMany = [ports: Port]

    static constraints = {
	    label nullable: false
	    location nullable: false
	    deviceType validator: { val, obj -> obj?.ports?.size() <= val.ports }
	    comments nullable: true

    }

	String toString() {
		label
	}
}
