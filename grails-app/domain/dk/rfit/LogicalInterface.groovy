package dk.rfit

class LogicalInterface {

	String name
	Boolean active

    static constraints = {
	    name nullable: false
	    active nullable: false
    }

	String toString() {
		name
	}
}
