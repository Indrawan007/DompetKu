package com.dompetku.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.dompetku.app.data.local.entity.CategorySummary;
import com.dompetku.app.data.local.entity.DailyTotal;
import com.dompetku.app.data.local.entity.MonthlyTrend;
import com.dompetku.app.data.local.entity.TransactionEntity;
import com.dompetku.app.data.local.entity.TransactionWithDetails;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __deletionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`amount`,`type`,`category_id`,`account_id`,`note`,`date`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        statement.bindString(3, entity.getType());
        if (entity.getCategoryId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getCategoryId());
        }
        statement.bindLong(5, entity.getAccountId());
        statement.bindString(6, entity.getNote());
        statement.bindLong(7, entity.getDate());
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`amount` = ?,`type` = ?,`category_id` = ?,`account_id` = ?,`note` = ?,`date` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        statement.bindString(3, entity.getType());
        if (entity.getCategoryId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getCategoryId());
        }
        statement.bindLong(5, entity.getAccountId());
        statement.bindString(6, entity.getNote());
        statement.bindLong(7, entity.getDate());
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final TransactionEntity transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransactionEntity.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransactionEntity.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransactionEntity.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TransactionWithDetails>> getAllWithDetails() {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type, \n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        ORDER BY t.date DESC, t.created_at DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super TransactionEntity> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionEntity>() {
      @Override
      @Nullable
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "account_id");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final TransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new TransactionEntity(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpAccountId,_tmpNote,_tmpDate,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByIdWithDetails(final long id,
      final Continuation<? super TransactionWithDetails> $completion) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        WHERE t.id = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionWithDetails>() {
      @Override
      @Nullable
      public TransactionWithDetails call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final TransactionWithDetails _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TransactionWithDetails>> getByDateRange(final long startDate,
      final long endDate) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        WHERE t.date BETWEEN ? AND ?\n"
            + "        ORDER BY t.date DESC, t.created_at DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionWithDetails>> getByTypeAndDateRange(final String type,
      final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        WHERE t.type = ?\n"
            + "        AND t.date BETWEEN ? AND ?\n"
            + "        ORDER BY t.date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, type);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionWithDetails>> getByCategoryAndDateRange(final long categoryId,
      final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        WHERE t.category_id = ?\n"
            + "        AND t.date BETWEEN ? AND ?\n"
            + "        ORDER BY t.date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionWithDetails>> getByAccount(final long accountId) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        WHERE t.account_id = ?\n"
            + "        ORDER BY t.date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionWithDetails>> search(final String query) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        WHERE t.note LIKE '%' || ? || '%'\n"
            + "           OR c.name LIKE '%' || ? || '%'\n"
            + "           OR a.name LIKE '%' || ? || '%'\n"
            + "        ORDER BY t.date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    _argIndex = 3;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> getTotalIncome(final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT COALESCE(SUM(amount), 0.0)\n"
            + "        FROM transactions\n"
            + "        WHERE type = 'INCOME'\n"
            + "        AND date BETWEEN ? AND ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<Double>() {
      @Override
      @NonNull
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final double _tmp;
            _tmp = _cursor.getDouble(0);
            _result = _tmp;
          } else {
            _result = 0.0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> getTotalExpense(final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT COALESCE(SUM(amount), 0.0)\n"
            + "        FROM transactions\n"
            + "        WHERE type = 'EXPENSE'\n"
            + "        AND date BETWEEN ? AND ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<Double>() {
      @Override
      @NonNull
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final double _tmp;
            _tmp = _cursor.getDouble(0);
            _result = _tmp;
          } else {
            _result = 0.0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> getTotalExpenseByCategory(final long categoryId, final long startDate,
      final long endDate) {
    final String _sql = "\n"
            + "        SELECT COALESCE(SUM(amount), 0.0)\n"
            + "        FROM transactions\n"
            + "        WHERE type = 'EXPENSE'\n"
            + "        AND category_id = ?\n"
            + "        AND date BETWEEN ? AND ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<Double>() {
      @Override
      @NonNull
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final double _tmp;
            _tmp = _cursor.getDouble(0);
            _result = _tmp;
          } else {
            _result = 0.0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getTransactionCount() {
    final String _sql = "SELECT COUNT(*) FROM transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<CategorySummary>> getCategorySummary(final String type, final long startDate,
      final long endDate) {
    final String _sql = "\n"
            + "        SELECT c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               COALESCE(SUM(t.amount), 0.0) AS totalAmount,\n"
            + "               COUNT(t.id) AS transactionCount\n"
            + "        FROM transactions t\n"
            + "        INNER JOIN categories c ON t.category_id = c.id\n"
            + "        WHERE t.type = ?\n"
            + "        AND t.date BETWEEN ? AND ?\n"
            + "        GROUP BY t.category_id\n"
            + "        ORDER BY totalAmount DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, type);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions",
        "categories"}, new Callable<List<CategorySummary>>() {
      @Override
      @NonNull
      public List<CategorySummary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategoryName = 0;
          final int _cursorIndexOfCategoryColor = 1;
          final int _cursorIndexOfCategoryIcon = 2;
          final int _cursorIndexOfTotalAmount = 3;
          final int _cursorIndexOfTransactionCount = 4;
          final List<CategorySummary> _result = new ArrayList<CategorySummary>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategorySummary _item;
            final String _tmpCategoryName;
            _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            final String _tmpCategoryColor;
            _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            final String _tmpCategoryIcon;
            _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            _item = new CategorySummary(_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpTotalAmount,_tmpTransactionCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<MonthlyTrend>> getMonthlyTrend(final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT strftime('%Y-%m', date / 1000, 'unixepoch', 'localtime') AS monthYear,\n"
            + "               COALESCE(SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END), 0.0) AS totalIncome,\n"
            + "               COALESCE(SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END), 0.0) AS totalExpense\n"
            + "        FROM transactions\n"
            + "        WHERE date BETWEEN ? AND ?\n"
            + "        GROUP BY monthYear\n"
            + "        ORDER BY monthYear ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<MonthlyTrend>>() {
      @Override
      @NonNull
      public List<MonthlyTrend> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMonthYear = 0;
          final int _cursorIndexOfTotalIncome = 1;
          final int _cursorIndexOfTotalExpense = 2;
          final List<MonthlyTrend> _result = new ArrayList<MonthlyTrend>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MonthlyTrend _item;
            final String _tmpMonthYear;
            _tmpMonthYear = _cursor.getString(_cursorIndexOfMonthYear);
            final double _tmpTotalIncome;
            _tmpTotalIncome = _cursor.getDouble(_cursorIndexOfTotalIncome);
            final double _tmpTotalExpense;
            _tmpTotalExpense = _cursor.getDouble(_cursorIndexOfTotalExpense);
            _item = new MonthlyTrend(_tmpMonthYear,_tmpTotalIncome,_tmpTotalExpense);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<DailyTotal>> getDailyTotal(final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT strftime('%Y-%m-%d', date / 1000, 'unixepoch', 'localtime') AS date,\n"
            + "               COALESCE(SUM(amount), 0.0) AS totalAmount,\n"
            + "               type\n"
            + "        FROM transactions\n"
            + "        WHERE date BETWEEN ? AND ?\n"
            + "        GROUP BY date, type\n"
            + "        ORDER BY date ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<DailyTotal>>() {
      @Override
      @NonNull
      public List<DailyTotal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final int _cursorIndexOfType = 2;
          final List<DailyTotal> _result = new ArrayList<DailyTotal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyTotal _item;
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new DailyTotal(_tmpDate,_tmpTotalAmount,_tmpType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionWithDetails>> getRecent(final int limit) {
    final String _sql = "\n"
            + "        SELECT t.id, t.amount, t.type,\n"
            + "               t.category_id AS categoryId,\n"
            + "               c.name AS categoryName,\n"
            + "               c.color AS categoryColor,\n"
            + "               c.icon AS categoryIcon,\n"
            + "               t.account_id AS accountId,\n"
            + "               a.name AS accountName,\n"
            + "               t.note, t.date, t.created_at AS createdAt\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.category_id = c.id\n"
            + "        LEFT JOIN accounts a ON t.account_id = a.id\n"
            + "        ORDER BY t.date DESC, t.created_at DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions", "categories",
        "accounts"}, new Callable<List<TransactionWithDetails>>() {
      @Override
      @NonNull
      public List<TransactionWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfType = 2;
          final int _cursorIndexOfCategoryId = 3;
          final int _cursorIndexOfCategoryName = 4;
          final int _cursorIndexOfCategoryColor = 5;
          final int _cursorIndexOfCategoryIcon = 6;
          final int _cursorIndexOfAccountId = 7;
          final int _cursorIndexOfAccountName = 8;
          final int _cursorIndexOfNote = 9;
          final int _cursorIndexOfDate = 10;
          final int _cursorIndexOfCreatedAt = 11;
          final List<TransactionWithDetails> _result = new ArrayList<TransactionWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionWithDetails _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final String _tmpCategoryColor;
            if (_cursor.isNull(_cursorIndexOfCategoryColor)) {
              _tmpCategoryColor = null;
            } else {
              _tmpCategoryColor = _cursor.getString(_cursorIndexOfCategoryColor);
            }
            final String _tmpCategoryIcon;
            if (_cursor.isNull(_cursorIndexOfCategoryIcon)) {
              _tmpCategoryIcon = null;
            } else {
              _tmpCategoryIcon = _cursor.getString(_cursorIndexOfCategoryIcon);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final String _tmpAccountName;
            _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TransactionWithDetails(_tmpId,_tmpAmount,_tmpType,_tmpCategoryId,_tmpCategoryName,_tmpCategoryColor,_tmpCategoryIcon,_tmpAccountId,_tmpAccountName,_tmpNote,_tmpDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
