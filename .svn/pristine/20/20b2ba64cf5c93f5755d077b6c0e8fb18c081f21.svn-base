package com.hbw.library.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class JsonStringPostRequest extends Request<String> {
    private Map<String, String> mMap;
    private Listener<String> mListener;
    protected static final String TYPE_UTF8_CHARSET = "charset=UTF-8";

    public JsonStringPostRequest(String url, Listener<String> listener,
                                 ErrorListener errorListener, Map map) {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mMap = map;

    }

    public JsonStringPostRequest(String url, Listener<String> listener,
                                 ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mListener = listener;

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {

        return mMap;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, "utf-8");
            // new String(response.data,
            // HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

}
