    import javax.swing.*;
    import java.awt.*;

    public class ThreadBot implements Runnable {
    	private InterfaceIA interfaceIA;
    	private int gametype;
    	private Bot bot;
        private API interfaceMJ;

    	public ThreadBot(InterfaceIA interfaceIA, int gametype, API interfaceMJ) {
            this.interfaceMJ = interfaceMJ;
    		this.interfaceIA = interfaceIA;
    		this.gametype = gametype;

    	}

    	@Override
    	public void run() {

    		if(this.gametype == 1) {
    			this.bot = new BotAleatoire("Bot Aléatoire", this.interfaceIA);
    		} else if(this.gametype == 2) {
    			this.bot = new BotGlouton("Bot Glouton", this.interfaceIA);
    		} else if(this.gametype == 3) {
    			this.bot = new BotAmeliore("ame", this.interfaceIA);
    		}
            this.bot.setAPI(this.interfaceMJ);
            this.bot.startBot();

    	}
    }
