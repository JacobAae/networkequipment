package dk.rfit

import grails.test.mixin.*
import spock.lang.*

@TestFor(LogicalInterfaceController)
@Mock(LogicalInterface)
class LogicalInterfaceControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'Access net'
        params["active"] = 'true'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.logicalInterfaceList
            model.logicalInterfaceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.logicalInterface!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def logicalInterface = new LogicalInterface()
            logicalInterface.validate()
            controller.save(logicalInterface)

        then:"The create view is rendered again with the correct model"
            model.logicalInterface!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            logicalInterface = new LogicalInterface(params)

            controller.save(logicalInterface)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/logicalInterface/show/1'
            controller.flash.message != null
            LogicalInterface.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def logicalInterface = new LogicalInterface(params)
            controller.show(logicalInterface)

        then:"A model is populated containing the domain instance"
            model.logicalInterface == logicalInterface
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def logicalInterface = new LogicalInterface(params)
            controller.edit(logicalInterface)

        then:"A model is populated containing the domain instance"
            model.logicalInterface == logicalInterface
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/logicalInterface/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def logicalInterface = new LogicalInterface()
            logicalInterface.validate()
            controller.update(logicalInterface)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.logicalInterface == logicalInterface

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            logicalInterface = new LogicalInterface(params).save(flush: true)
            controller.update(logicalInterface)

        then:"A redirect is issued to the show action"
            logicalInterface != null
            response.redirectedUrl == "/logicalInterface/show/$logicalInterface.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/logicalInterface/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def logicalInterface = new LogicalInterface(params).save(flush: true)

        then:"It exists"
            LogicalInterface.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(logicalInterface)

        then:"The instance is deleted"
            LogicalInterface.count() == 0
            response.redirectedUrl == '/logicalInterface/index'
            flash.message != null
    }
}
