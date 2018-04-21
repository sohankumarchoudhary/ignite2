using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ignite
{
    /// <summary>
    /// Interface for service proxy interaction.
    /// Actual service class (<see cref="MapService{TK,TV}"/>) does not have to implement this interface. 
    /// Target method/property will be searched by signature (name, arguments).
    /// </summary>
    interface ImapServices<TK, TV>
    {
        /// <summary>
        /// Puts an entry to the map.
        /// </summary>
        /// <param name="key">The key.</param>
        /// <param name="value">The value.</param>
        void Put(TK key, TV value);

        /// <summary>
        /// Gets an entry from the map.
        /// </summary>
        /// <param name="key">The key.</param>
        /// <returns>Entry value.</returns>
        TV Get(TK key);

        /// <summary>
        /// Clears the map.
        /// </summary>
        void Clear();

        /// <summary>
        /// Gets the size of the map.
        /// </summary>
        /// <value>
        /// The size.
        /// </value>
        int Size { get; }
    }
}

