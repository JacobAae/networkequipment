package dk.rfit

class Profile {

	String profileName
	String description
	Boolean active

    static constraints = {
	    profileName nullable: false
	    description nullable: true, blank: true
	    active nullable: false
    }

	String toString() {
		profileName
	}
}
