    import javax.swing.*;
    import java.awt.*;

    public class ThreadBot implements Runnable {
    	private int gametype;
    	private Bot bot;
        private API interfaceMJ;

        public ThreadBot(int gametype, API interfaceMJ) {
            this.interfaceMJ = interfaceMJ;
            this.gametype = gametype;

        }

        @Override
        public void run() {

          if(this.gametype == 1) {
            this.bot = new BotAleatoire("Bot Aléatoire");
         } else if(this.gametype == 2) {
             this.bot = new BotGlouton("Bot Glouton");
         } else if(this.gametype == 3) {
             this.bot = new BotAmeliore("ame");
         }
         this.bot.setAPI(this.interfaceMJ);
         this.bot.startBot();

     }
 }
