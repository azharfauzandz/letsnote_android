package tekmob.letsnote.events;

import tekmob.letsnote.models.GetNotesResultModel;

/**
 * Created by feaza on 10/9/2016.
 */

public class GetNotesResultEvent extends BaseEvent {
    private GetNotesResultModel getNotesResultModel;

    public GetNotesResultEvent(boolean success, GetNotesResultModel getNotesResultModel) {
        status = success ? OK : FAIL;
        this.getNotesResultModel =  getNotesResultModel;
    }

    public GetNotesResultEvent(boolean success, String message) {
        status = success ? OK :FAIL;
        this.message = message;
    }

    public GetNotesResultModel getGetNotesResultModel() {
        return getNotesResultModel;
    }
}
