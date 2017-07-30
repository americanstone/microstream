package net.jadoth.traversal;

import net.jadoth.collections.types.XReplacingBag;


public final class TraverserXCollectionMutable implements TypeTraverser<XReplacingBag<Object>>
{
	@Override
	public final void traverseReferences(
		final XReplacingBag<Object> instance        ,
		final TraversalMutator      mutator         ,
		final TraversalEnqueuer     enqueuer        ,
		final MutationListener      mutationListener
	)
	{
		try
		{
			instance.substitute(current ->
			{
				final Object returned = mutator.mutateReference(current, instance, enqueuer);

				if(mutationListener != null)
				{
					mutationListener.registerChange(instance, current, returned);
				}
				
				// note: if the current (now prior) value has to be enqueued, the acceptor can do that internally
				enqueuer.enqueue(returned);
				
				return returned;
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
		
	}

	@Override
	public final void traverseReferences(
		final XReplacingBag<Object> instance,
		final TraversalAcceptor     acceptor,
		final TraversalEnqueuer     enqueuer
	)
	{
		try
		{
			instance.iterate(current ->
			{
				acceptor.acceptReference(current, instance, enqueuer);
				enqueuer.enqueue(current);
			});
		}
		catch(final AbstractTraversalSkipSignal s)
		{
			// any skip signal reaching this point means abort the whole instance, in one way or another
		}
	}
	
}
