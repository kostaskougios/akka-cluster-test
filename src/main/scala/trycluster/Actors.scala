package trycluster

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.cluster.ClusterEvent.{MemberEvent, MemberLeft, MemberUp}
import akka.cluster.typed.{Cluster, Subscribe}
import com.typesafe.config.Config
import com.typesafe.scalalogging.Logger

/**
  * @author kostas.kougios
  *         16/03/18 - 19:38
  */
class Actors(config: Config)
{
	private val logger = Logger(getClass)

	val guardianActor = Behaviors.setup[Any] { ctx =>
		val cluster = Cluster(ctx.system)

		val address = cluster.selfMember.address
		cluster.subscriptions ! Subscribe(ctx.self, classOf[MemberEvent])

		Behaviors.immutable[Any] {
			(ctx, op) =>
				op match {
					case MemberUp(member) =>
						logger.info(s"$address : Joined the cluster : $member")
					case MemberLeft(member) =>
						logger.info(s"$address : Leaving the cluster : $member")
					case x =>
						logger.warn(s"Unknown msg received : $x")
				}
				Behaviors.same
		}
	}
	val system = ActorSystem(guardianActor, "distmem", config)

}
