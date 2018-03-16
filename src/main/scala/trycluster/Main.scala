package trycluster

import com.typesafe.config.{ConfigFactory, ConfigValueFactory}

/**
  * @author kostas.kougios
  *         16/03/18 - 19:37
  */
object Main extends App
{
	private val defaultConfig = ConfigFactory.parseResources("server.conf").resolve

	println("---------------- Starting server 1 -----------------------------------")
	new Actors(defaultConfig.withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(2551)))
	Thread.sleep(3000)
	println("---------------- Starting server 2 -----------------------------------")
	new Actors(defaultConfig.withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(2552)))

	Thread.sleep(5000)
	println("---------------- Terminating -----------------------------------------")
	System.exit(0)
}
