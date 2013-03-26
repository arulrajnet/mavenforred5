package net.arulraj.demo;


import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.stream.IServerStream;
import org.slf4j.Logger;

/**
 * @author Arul
 *
 */
public class App extends ApplicationAdapter
{
  private IScope appScope;

  private IServerStream serverStream;

  private static Logger log = Red5LoggerFactory.getLogger(App.class, "red5app");

  @Override
  public boolean appStart(IScope app) {
    super.appStart(app);
    log.info("red5app appStart");
    System.out.println("red5app appStart");
    appScope = app;
    return true;
  }

  @Override
  public boolean appConnect(IConnection conn, Object[] params) {
    log.info("red5app appConnect");
    return super.appConnect(conn, params);
  }

  @Override
  public void appDisconnect(IConnection conn) {
    log.info("red5app appDisconnect");
    if (appScope == conn.getScope() && serverStream != null) {
      serverStream.close();
    }
    super.appDisconnect(conn);
  }
}
