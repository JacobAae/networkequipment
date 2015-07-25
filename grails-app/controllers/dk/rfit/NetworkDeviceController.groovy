package dk.rfit

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NetworkDeviceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NetworkDevice.list(params), model:[networkDeviceCount: NetworkDevice.count()]
    }

    def show(NetworkDevice networkDevice) {
        respond networkDevice
    }

    def create() {
        respond new NetworkDevice(params)
    }

    @Transactional
    def save(NetworkDevice networkDevice) {
        if (networkDevice == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (networkDevice.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond networkDevice.errors, view:'create'
            return
        }

        networkDevice.save flush:true

	    networkDevice.deviceType.ports.times {
		    networkDevice.addToPorts(used: false, xpc: 0, mic: 0, portNumber: it, physicalInterface: PhysicalInterface.T, logicalInterface:  LogicalInterface.first(), profile: Profile.first() )
	    }


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'networkDevice.label', default: 'NetworkDevice'), networkDevice.id])
                redirect networkDevice
            }
            '*' { respond networkDevice, [status: CREATED] }
        }
    }

    def edit(NetworkDevice networkDevice) {
        respond networkDevice
    }

    @Transactional
    def update(NetworkDevice networkDevice) {
        if (networkDevice == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (networkDevice.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond networkDevice.errors, view:'edit'
            return
        }

        networkDevice.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'networkDevice.label', default: 'NetworkDevice'), networkDevice.id])
                redirect networkDevice
            }
            '*'{ respond networkDevice, [status: OK] }
        }
    }

    @Transactional
    def delete(NetworkDevice networkDevice) {

        if (networkDevice == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        networkDevice.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'networkDevice.label', default: 'NetworkDevice'), networkDevice.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'networkDevice.label', default: 'NetworkDevice'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
