package tekmob.letsnote.events;

import tekmob.letsnote.models.DummyModel;

/**
 * Created by feaza on 9/27/2016.
 */
public class GetDummyResultEvent extends BaseEvent {
    private DummyModel dummyModel;

    public GetDummyResultEvent(boolean success, DummyModel dummyModel) {
        status = success ? OK : FAIL;
        this.dummyModel = dummyModel;
    }

    public GetDummyResultEvent(boolean success, String message) {
        status = success ? OK : FAIL;
        this.message = message;
    }

    public DummyModel getDummyModel() {
        return dummyModel;
    }
}
