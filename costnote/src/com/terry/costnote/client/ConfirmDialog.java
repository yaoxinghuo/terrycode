package com.terry.costnote.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 6, 2009 9:24:21 PM
 */
public class ConfirmDialog extends DialogBox {
	public ConfirmDialog(String title, String text, String okLabel,
			String canceLabel) {
		setText(title);
		this.setModal(true);
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);

		layout.setHTML(0, 0, text);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(0, 0, 2);
		HorizontalPanel hp = new HorizontalPanel();
		Button ok = new Button(okLabel);
		hp.add(ok);
		hp.add(new HTML("&nbsp;&nbsp;"));
		Button cancel = new Button(canceLabel);
		hp.add(cancel);
		layout.setWidget(1, 1, hp);
		cellFormatter.setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_RIGHT);
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				onOK();
			}
		});
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				onCancel();
			}
		});
		setWidget(layout);
	}

	public void onOK() {
		this.hide();
	}

	public void onCancel() {
		this.hide();
	}
}
