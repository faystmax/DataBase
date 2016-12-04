/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemOtdel;

/**
 *
 * @author tigler
 */
public class ListenerCloseForm {

    UpdatesDataInForms updatesDataInForms;

    public ListenerCloseForm(UpdatesDataInForms updatesDataInForms) {
        this.updatesDataInForms = updatesDataInForms;
    }

    void event() {
        updatesDataInForms.addDataInTable();
    }
}
