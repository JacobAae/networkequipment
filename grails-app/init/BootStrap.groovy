import dk.rfit.LogicalInterface
import dk.rfit.Profile

class BootStrap {

	def init = { servletContext ->

		if( !Profile.count() ) {
			[
					"Backbone link",
					"Core link",
					"Core link mikrotik",
					"Servernet",
					"Bruger"
			].each {
				Profile profile = new Profile(profileName: it, active: true)
				profile.save(failOnError: true)
			}
		}
		if( !LogicalInterface.count() ) {
			[
					"access",
					"handel",
					"video",
					"h11access",
					"mgmt"
			].each {
				LogicalInterface logicalInterface = new LogicalInterface(name: it, active: true)
				logicalInterface.save(failOnError: true)
			}
		}

	}
	def destroy = {
	}
}
