/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.struts.publicidad.action;
import org.apache.struts.publicidad.model.MessageStore;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
        private String userName;
	private MessageStore messageStore;
	private static int helloCount = 0;
	public String execute() throws Exception {
		helloCount++;
		messageStore = new MessageStore() ;
                if (userName != null) {
                    messageStore.setMessage( messageStore.getMessage() + " " + userName);
		}
		return SUCCESS;
	}

	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}
        public int getHelloCount() {
            return helloCount;
        }
        public void setHelloCount(int helloCount) {
            HelloWorldAction.helloCount = helloCount;
        }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
        
}