using Apache.Ignite.Core;
using Apache.Ignite.Core.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ignite
{

    class Services
    {



        /// <summary>
        /// Runs the example.
        /// </summary>
        [STAThread]
        public static void Main()
        {
            using (var ignite = Ignition.Start())
            {
                Console.WriteLine(">>> Services example started.");
                Console.WriteLine();

                // Deploy a service
                var svc = new MapService<int, string>();
                Console.WriteLine(">>> Deploying service to all nodes...");
                ignite.GetServices().DeployNodeSingleton("service", svc);

                // Get a sticky service proxy so that we will always be contacting the same remote node.
                try
                {
                    var prx = ignite.GetServices().GetServiceProxy<ImapServices<int, string>>("service", true);
                    for (var i = 0; i < 10; i++)
                        prx.Put(i, i.ToString());

                    var mapSize = prx.Size;

                    Console.WriteLine(">>> Map service size: " + mapSize);

                    ignite.GetServices().CancelAll();

                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                }



                Console.WriteLine();
                Console.WriteLine(">>> Example finished, press any key to exit ...");
                Console.ReadKey();
            }
        }
    }
}


