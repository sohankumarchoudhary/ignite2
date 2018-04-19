using Apache.Ignite.Core;
using Apache.Ignite.Core.Cache;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ignite
{
    class Nodecls
    {
        public static void Main(string[] args)
        {
            IIgnite ignite = Ignition.Start();
            ICache<int, string> cache = ignite.GetOrCreateCache<int, string>("test");
            cache.Put(1, "Hello, World!");
            Console.WriteLine(cache.Get(1));
            Console.Read();
        }
    }
}
