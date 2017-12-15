package com.kodeartisan.kotlintemplate.data.source.local.sqliteAsset

import android.arch.persistence.db.SupportSQLiteOpenHelper

/**
 * Implements [SupportSQLiteOpenHelper.Factory] using the SQLite implementation in the
 * framework.
 */
class AssetSQLiteOpenHelperFactory : SupportSQLiteOpenHelper.Factory {
    override fun create(configuration: SupportSQLiteOpenHelper.Configuration): SupportSQLiteOpenHelper {
        return AssetSQLiteOpenHelper(
                configuration.context, configuration.name, null,
                configuration.version, configuration.errorHandler, configuration.callback
        )
    }
}
