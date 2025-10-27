/* package co.edu.uco.nose.controller.dto;

import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    private List<String> messages;
    private List<T> data;
    private Boolean responseSucceded;

    public List<String> getMessages() {
        return messages;
    }

    public Response( final ArrayList<String> messages, final ArrayList<T> data, final Boolean responseSucceded) {
        setMessages(messages);
        setData(data);
        setResponseSucceded(responseSucceded);

    }

    public static <T> Response<T> createSuccededResponse(){
        return new Response<>(new ArrayList<String>(), new ArrayList<T>(), true);
    }

    public static <T> Response<T> createFailedResponse(){
        return new Response<>(new ArrayList<String>(), new ArrayList<T>(), false);

    public static <T> Response<T> createSuccededResponse(final List<T> data){
        return new Response<>(new ArrayList<String>(), data, true);
    }

    public static <T> Response createFailedResponse(final List<T> data){
        return new Response(new ArrayList<String>(), data, false);
    }

    public void setMessages(List<String> messages) {
        this.messages = ObjectHelper.getDefault();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data =;
    }

    public Boolean getResponseSucceded() {
        return responseSucceded;
    }

    public void setResponseSucceded(Boolean responseSucceded) {
        this.responseSucceded = responseSucceded;
    }
}
*/