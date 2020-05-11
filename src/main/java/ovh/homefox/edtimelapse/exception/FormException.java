/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.exception;

/**
 *
 * @author aymer
 */
public class FormException extends Exception {
    
    public FormException(String error){
        super(error);
    }
}
