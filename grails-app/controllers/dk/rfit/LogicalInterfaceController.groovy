package dk.rfit

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LogicalInterfaceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LogicalInterface.list(params), model:[logicalInterfaceCount: LogicalInterface.count()]
    }

    def show(LogicalInterface logicalInterface) {
        respond logicalInterface
    }

    def create() {
        respond new LogicalInterface(params)
    }

    @Transactional
    def save(LogicalInterface logicalInterface) {
        if (logicalInterface == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (logicalInterface.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond logicalInterface.errors, view:'create'
            return
        }

        logicalInterface.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'logicalInterface.label', default: 'LogicalInterface'), logicalInterface.id])
                redirect logicalInterface
            }
            '*' { respond logicalInterface, [status: CREATED] }
        }
    }

    def edit(LogicalInterface logicalInterface) {
        respond logicalInterface
    }

    @Transactional
    def update(LogicalInterface logicalInterface) {
        if (logicalInterface == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (logicalInterface.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond logicalInterface.errors, view:'edit'
            return
        }

        logicalInterface.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'logicalInterface.label', default: 'LogicalInterface'), logicalInterface.id])
                redirect logicalInterface
            }
            '*'{ respond logicalInterface, [status: OK] }
        }
    }

    @Transactional
    def delete(LogicalInterface logicalInterface) {

        if (logicalInterface == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        logicalInterface.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'logicalInterface.label', default: 'LogicalInterface'), logicalInterface.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'logicalInterface.label', default: 'LogicalInterface'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
