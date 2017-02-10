package net.jadoth.exceptions;

/**
 * Proper version of {@link IndexOutOfBoundsException} /  {@link ArrayIndexOutOfBoundsException}.
 * <p>
 * As usual, they put programmatic relevant data into contextless plain strings in class
 * {@link ArrayIndexOutOfBoundsException}.
 * Funny thing is with this class they at least tried to do it right (one parameter index), but still failed
 * hilariously (missing array bound parameter and again stuffing the argument value into a plain string).
 * <p>
 * Also note from an architectual / design / API point of view that (apart from {@link ArrayCapacityException}
 * beeing a JVM-technical special case), index bounds exceptions should not distinct between comming from an
 * array or another index-based data structure as this is an implementation detail. Especially since Java exceptions
 * are hardcoded classes instead of multiple-inheritance-of-type-viable proper types (interfaces), it even MUST not
 * be differentiated.
 * <p>
 * So again, one has to do everything by oneself properly.
 *
 * @author TM
 *
 */
public class IndexBoundsException extends IndexOutOfBoundsException
{
	///////////////////////////////////////////////////////////////////////////
	// constants        //
	/////////////////////

	static final String MESSAGE_BODY = "Index out of bounds";



	///////////////////////////////////////////////////////////////////////////
	// instance fields //
	////////////////////

	/**
	 * The lowest possible index value, inclusive (e.g. 0 for array indices)
	 */
	private final long startIndex;

	/**
	 * The upper index bound, exclusive (e.g. array.length for array indices)
	 *
	 */
	private final long indexBound;

	/**
	 * The index value that is out of bounds.
	 */
	private final long index;



	///////////////////////////////////////////////////////////////////////////
	// constructors //
	/////////////////

	public IndexBoundsException(final long indexBound)
	{
		this(indexBound, null);
	}

	public IndexBoundsException(final long indexBound, final String message)
	{
		// index may overflow here as it is not deemed relevant by choice of constructor
		this(0, indexBound, indexBound, message);
	}

	public IndexBoundsException(final long indexBound, final long index)
	{
		this(0, indexBound, index, null);
	}

	public IndexBoundsException(final long indexBound, final long index, final String message)
	{
		this(0, indexBound, index, message);
	}

	public IndexBoundsException(final long startIndex, final long indexBound, final long index)
	{
		this(startIndex, indexBound, index, null);
	}

	public IndexBoundsException(final long startIndex, final long indexBound, final long index, final String message)
	{
		super(message);
		this.startIndex = startIndex;
		this.indexBound = indexBound;
		this.index      = index     ;
	}



	///////////////////////////////////////////////////////////////////////////
	// declared methods //
	/////////////////////

	public final long startIndex()
	{
		return this.startIndex;
	}

	public final long indexBound()
	{
		return this.indexBound;
	}

	/**
	 * The index value used in the accessing attempt causing this exception.
	 * <p>
	 * Note that this value might have overflown depending on the reporting logic.
	 *
	 * @return
	 */
	public final long index()
	{
		return this.index;
	}

	/**
	 * Sadly, the Throwable implementation uses #getMessage() directly to print the exception.
	 * This is a concern conflict: getMessage should actually be the getter for the explicit message.
	 * But it is used as the String representating method as well.
	 * So a output message generically assembling the output string must override the getter.
	 * As this hides the actual getting functionality, a workaround accessor method has to be provided
	 * for potential subclasses.
	 *
	 * @return the explicit message string passed to the constructor when creating this instance.
	 */
	// I love noobish JDK developers. And why is the adjective "Throwable" not an interface in the first place.
	public final String message()
	{
		return super.getMessage();
	}

	public String assembleDetailString()
	{
		return MESSAGE_BODY + ": " + this.index + " not in [" + this.startIndex + ";" + this.indexBound + "[";
	}

	protected String assembleExplicitMessageAddon()
	{
		final String explicitMessage = super.getMessage();
		return explicitMessage == null ? "" : " (" + explicitMessage + ")";
	}

	public String assembleOutputString()
	{
		return this.assembleDetailString() + this.assembleExplicitMessageAddon();
	}



	///////////////////////////////////////////////////////////////////////////
	// override methods //
	/////////////////////

	/**
	 * Returns an assembled output String due to bad method design in {@link Throwable}.
	 * Albeit being named "getMessage" by the JDK developers, this method should be seen as "assembleOutputString" as this is its purpose.
	 * For the actual message getter, see {@link #message()}.
	 *
	 * @return this exception type's generic string plus an explicit message if present.
	 */
	@Override
	public String getMessage() // intentionally not final to enable subclasses to change the behaviour again
	{
		return this.assembleOutputString();
	}



	// ridiculous hacky buggy security hole misconceptioned serialization, but if need be...
	private static final long serialVersionUID = 1489211066951377456L;
}
