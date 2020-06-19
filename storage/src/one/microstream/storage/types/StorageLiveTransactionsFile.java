package one.microstream.storage.types;

public interface StorageLiveTransactionsFile
extends StorageTransactionsFile, StorageLiveFile<StorageLiveTransactionsFile>
{
	// (10.06.2020 TM)FIXME: priv#49: StorageLiveTransactionsFile
	
	@Override
	public default StorageBackupTransactionsFile ensureBackupFile(final StorageBackupInventory backupInventory)
	{
		return backupInventory.ensureTransactionsFile(this);
	}
	
}
