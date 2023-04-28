package com.Interfaces;

import javafx.event.ActionEvent;

public interface ButtonAction {
    void onClickAdd(ActionEvent event);

    void onClickDelete(ActionEvent event);

    void onClickUpdate(ActionEvent event);

    void onClickClear(ActionEvent event);

    void onClickExport(ActionEvent event);

    void onClickRefresh(ActionEvent event);

}
