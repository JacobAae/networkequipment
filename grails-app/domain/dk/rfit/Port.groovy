package dk.rfit

class Port {

	Boolean used
	Integer xpc
	Integer mic
	Integer portNumber


	PhysicalInterface physicalInterface
	Profile profile
	LogicalInterface logicalInterface

	static belongsTo = [networkdevice: NetworkDevice]

    static constraints = {
	    xpc min: 0
	    mic min: 0
	    portNumber min: 0, max: 47
	    physicalInterface nullable: false
	    profile nullable: false
	    logicalInterface nullable: false
	    networkdevice unique: ['portNumber']
    }

	String toString() {
		"${xpc}/${mic}/${portNumber} ${physicalInterface} - ${logicalInterface}"
	}
}
