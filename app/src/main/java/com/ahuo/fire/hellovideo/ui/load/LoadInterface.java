package com.ahuo.fire.hellovideo.ui.load;

public interface LoadInterface {

    public interface onRetryListener{
        void onClick();
    }
    public interface  onTipListener{
        void onConfirm();
        void onCancel();
    }


}
