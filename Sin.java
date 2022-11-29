package library;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import base.BaseButton;
import base.RecycleAdapter;

public class Sin { //══════════════════════════════════════════════════════════════════  CLASS Sin  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄

@IntDef() @Retention(RetentionPolicy.SOURCE) public @interface RawPos       {} //═══════  RawPos i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
@IntDef() @Retention(RetentionPolicy.SOURCE) public @interface FilterPos    {} //════  FilterPos i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface ConfigChange   { void onConfigChange(@NonNull Configuration config); }

public interface OnBackPress    { boolean onBackPress(); }

//════════════════════════════════════════════════════════════════════════════════════  Callback i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface Callback extends OnPause, OnResume, OnDestroy {}
public interface OnPause    { boolean   onPause(); }
public interface OnResume   { void      onResume(); }
public interface OnDestroy  { void      onDestroy(); }

public interface Resumable  { void resume(); }
public interface Pausable   { void pause(); boolean isPaused(); }
public interface PausableWait extends Pausable { void pauseWait(); }


@FunctionalInterface public interface Action    { void action(Sen.Action button); }
@FunctionalInterface public interface SetButton { void set(BaseButton button, int i); }
@FunctionalInterface public interface SetSlider { void set(Slider slider, int i); }
@FunctionalInterface public interface SetImageView { void set(ImageView button, int i); }


public interface ColorSpace extends Printable //═════════════════════════════════════  Printable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   @ColorInt int   toInt();
    int[]           toArgbArr();
}

public interface Loading //════════════════════════════════════════════════════════════  Loading i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void loading(int num);
    void loading(float num);
    void loading(boolean b);
    void loading(CharSequence s);
}

public interface ColorFilter
{   void setFilter(ColorMatrix matrix);
    void resetFilter();
}

public interface ColorFilterable //══════════════════════════════════════════════════════  Filterable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void setTemp    (float v);
    void setTint    (float v);
    void setExposure(float v);
    void setContrast(float v);
    void setHighlight(float v);
    void setShadow  (float v);
    void setHue     (float v);
    void setVibrance(float v);
    void setBright  (float v);
    
    void applyFilter();
    void pauseFilter();
    void resumeFilter();
    void resetFilter();
}

//════════════════════════════════════════════════════════════════════════════════════  Backable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface OnBack     { boolean back(); }
public interface Backable   { boolean back(); }
public interface BackableOn extends Backable
{   void setOnBack(OnBack onBack);

}

public interface HidableView //══════════════════════════════════════════════  HidableView //════════════════════════════════════════════════════  HidableView i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   boolean show();
    boolean hide();
    HidableView addOppositeView(View... views);
}
public interface HidableViewAnimAdv extends HidableViewAnim, ShowHideAnim
{   boolean showAnim(Anim.Preset preset);
    boolean hideAnim(Anim.Preset preset);
    
    HidableViewAnimAdv addOppositeView(View... views);
    /** copy showAnim and hideAnim */
    HidableViewAnimAdv cloneHidableViewAnim(ShowHideAnim hidableViewAnim);
    
    HidableViewAnimAdv setShowAnim(Anim.Preset animPreset);
    HidableViewAnimAdv setHideAnim(Anim.Preset animPreset);
}

public interface HidableViewAnim
{   /** unhide with animation */
    boolean showAnim();
    /** hide with animation */
    boolean hideAnim();
    HidableViewAnim addOppositeView(View... views);
}


public interface ShowHideAnim
{   ShowHideAnim setShowAnim(Anim.Preset animPreset);
    ShowHideAnim setHideAnim(Anim.Preset animPreset);
}

//════════════════════════════════════════════════════════════════════════════════════  Recycler i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface OnScrollLs { void onScroll(Recycler rv, int dx, int dy); }
public interface Recycler extends RecyclerBasic, Sin.OnDestroy
{   void    addOnScrollLs   (OnScrollLs onScrollLs);
    void    scrollToPosition(int pos);
    float   getScrollBarPos (int scrollbarH);
    void    setAdapt        (RecyclerAdapter adapter);
    float   getPos          ();
}

public interface RecyclerAdapter extends RecyclerBasic
{   RecyclerAdapter setRecycler (Recycler recycler);
    RecyclerAdapter setOnSelectMode(RecycleAdapter.OnSelectMode onSelect);
}

public interface RecyclerBasic
{   int     getItemCount();
    String  getItemText (int pos);
    String  getItemText ();
}

public interface IsVisible { boolean isVisible(int pos); } //════════════════════════  IsVisible i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

//════════════════════════════════════════════════════════════════════════════════  OnSwipeTouch i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface OnSwipeTouch   extends View.OnTouchListener, OnSwipe {}
public interface OnSwipe        extends OnSwipeUp, OnSwipeDown, OnSwipeLeft, OnSwipeRight {}
public interface OnSwipeUp      { void onSwipeUp(); }
public interface OnSwipeDown    { void onSwipeDown(); }
public interface OnSwipeLeft    { void onSwipeLeft(); }
public interface OnSwipeRight   { void onSwipeRight(); }


public interface Engine { void reload(); } //═══════════════════════════════════════════  Engine i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

//══════════════════════════════════════════════════════════════════════════════════════  Slider i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface OnSlideLs { void onSlide(int i); }
/** Slider without name */
public interface SliderRaw extends Resettable
{   void setRange   (int... range); // min, max, default
    void setMax     (int max);
    void setDefVal  (int defVal);
    void set        (int val);
    int getVal();
    void setBg      (int bg);
    void setOnSlideLs(OnSlideLs onSlide);
    void setOnSlideNumLs(OnSlideLs onSlide);
}

public interface Slider extends SliderRaw
{   void setText(CharSequence s);
}


public interface Lock //══════════════════════════════════════════════════════════════════  Lock i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void    lock();
    void    unlock();
    boolean isLocked();
    boolean getAndSwap();
    boolean swapAndGet();
}

public interface OnError { void run(); } //════════════════════════════════════════════  OnError i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface Pair <K, V> extends Sizable, IterablePair<K, V> //═══════════════════════  Pair i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void add    (K k, V v);
    void set    (int index, K k, V v);
    K getKey    (int i);
    V getValue  (int i);
    V getValue  (K key);
    V remove    (K k);
    void clear  ();
    int indexOf (K k);
    /** @return true if new element was added to list */
    boolean ensure(K k, V v);
}

public interface Iterator<T> { void iter(T t); } //════════════════════════════════  Iterator<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

//════════════════════════════════════════════════════════════════════════════════  IteratorI<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface IteratorI<T> { void iter(int i, T t); }
public interface Iterable<T>
{   void iter       (IteratorI<T> iterator);
    void iterBack   (IteratorI<T> iterator);
}

public interface IteratorBoo<T> { boolean iter(int i, T t); }
public interface IterableBoo<T> { void    iter(IteratorI<T> iterator); }

/** element Iterator */
public interface IteratorE<E> { void iter(E e); }
/** element Iterable */
public interface IterableE<E> { void iter(IteratorE<E> iterator); }

public interface IteratorEBoo<E> { boolean iter(E e); }

public interface IteratorPair<K, V> { void iter(K k, V v); }
public interface IterablePair<K, V>
{   void iter       (IteratorPair<K, V> iterator);
    void iterBack   (IteratorPair<K, V> iterator);
}

public interface Buildable { void build(); } //══════════════════════════════════════  Buildable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface BuildableT<T> { T build(); }

public interface ViewGroupable //════════════════════════════════════════════════  ViewGroupable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   ViewGroup toViewGroup();
}

public interface RadioGroup extends ViewGroupable //════════════════════════════════  RadioGroup i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void    removeAllViews();
    int     getIndex    ();             // get index of checked item
    void    check       (@IdRes int id);// check item at id
    void    checkIndex  (int index);    // check item at index
    ViewParent getParent();
}

@FunctionalInterface public interface OnClickLs { void onClick(); } //═══════════════  OnClickLs i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface Sizable { int size(); } //════════════════════════════════════════════  Sizable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface SizableInt extends Sizable { int size(int zIndex); }

/** Purpose: store data, or blueprints to get data */
public interface Data extends Sizable //══════════════════════════════════════════════════  Data i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{
}

public interface DataText extends Data
{   /** @return depth of data */
    int  getTextZ();
    /** usage for implemented class: add all elements at current【rowIndex】to the List
    @return a list of String at specific【rowIndex】
    @param rowIndex index of list in a lists */
    void getTextList(List<String> textList, int rowIndex);
}

public interface DataImage extends Data
{   /** @return a list of Bitmap at specific【rowIndex】
    @param rowIndex index of list in a lists */
    void getImageList(List<Bitmap> imageList, int rowIndex);
    void getImage(List<Bitmap> imageList, int rowIndex, int index);
    /** @return depth of data */
    int  getImageZ();
}

public interface DataDrawable extends Data
{   /** @return a list of Drawable at specific【rowIndex】
    @param rowIndex index of list in a lists */
    void getDrawableList(List<Drawable> drawableList, int rowIndex);
    /** @return depth of data */
    int  getDrawableZ();
}

//══════════════════════════════════════════════════════════════════════════════  DataController i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
/** Purpose: store data, extract data & apply data */
public interface DataController extends DataControllerText, DataControllerImage, DataControllerDrawable, Destroyable
{   void addData(Data data);
    <T> DataController setStuff(T stuff);
}

private interface DataControllerText
{   void    setText(TextView tv, @RawPos int pos, int zPos);
    
    String  getText(@RawPos int pos, int zPos);
    String  getText(@RawPos int pos);
    
    int textSize();
    int textZSize();
}

private interface DataControllerImage
{   void    setImage(ImageView imageView, @RawPos int pos, int zPos);
    Bitmap  getImage(@RawPos int pos, int zPos);
    
    void setImageWait(ImageView imageView, @FilterPos int pos, int i);
    
    int imageSize();
    int imageZSize();
}

private interface DataControllerDrawable
{   void    setDrawable(ImageView imageView, @RawPos int pos, int zPos);
    Drawable getDrawable(@RawPos int pos, int zPos);
    
    int drawableSize();
    int drawableZSize();
}

public interface Notifiable //══════════════════════════════════════════════════════  Notifiable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void notifyItemChanged(int pos);
}

/** can be set ThreadPool */
public interface Threaded { void setThreadPool(ThreadPool tp); } //═══════════════════  Threaded i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface Filterable<T> //════════════════════════════════════════════════  Filterable<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   Filter<T>   getFilter();
    void        filter(T key, int rowIndex, boolean paste);
    void        filter(T key, int rowIndex);
}
public interface FilterableText extends Filterable<CharSequence> {}


public interface Mark { int[] getMark(@FilterPos int pos); }

/** a simplified Filter<T> with generic removed */
public interface FilterRaw extends Mark, Sizable, Removable<Void>, Iterable<Integer>
{   void setSize(int size);
        /** remove an element at specific @FilterPos【pos】*/
    Void remove(@FilterPos int pos);
        /** remove selected pos in【selector】, and update【selector】accordingly */
    void remove(Selector    selector);
        /** remove selected pos in【selector】*/
    void remove(SelectorRaw selector);
    
        /** convert @FilterPos to @RawPos */
    @RawPos     int getRawPos   (@FilterPos int pos);
        /** convert @RawPos to @FilterPos */
    @FilterPos  int getFilterPos(@RawPos int rawPos);
    
        /** @return is filtered pos contain a @RawPos【rawPos】*/
    boolean isContainRaw(@RawPos int rawPos);
        /** @return an element at a specific @FilterPos【pos】*/
    <S> S getFilterItem(List<S> list, @FilterPos int pos);
    
    /** iterate through all filtered items <p>
     *  【iterator】: (@FilterPos int pos, @RawPos int rawPos) */
    void iter       (IteratorI<Integer> iterator);
    /** iterate backwards through all filtered items <p>
     *  【iterator】: (@FilterPos int pos, @RawPos int rawPos) */
    void iterBack   (IteratorI<Integer> iterator);
    /** iterate through all filtered items, return true in【iterator】to increase【count】
     * 【iterator】: (@FilterPos int pos, @RawPos int rawPos)
     *  @return 【count】*/
    int iterCount   (IteratorBoo<Integer> iterator);
}

public interface FilterableSimple { void filter(CharSequence s, boolean paste); }


/** find a key in a List of T and filter out result */
public interface Filter<T> extends FilterRaw //══════════════════════════════════════  Filter<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   /** find【key】in【texts】and filter out results.
    @param paste: whether this was pasted by user */
    void filter(@NonNull T key, List<? extends T> texts, boolean paste);
    /** find【key】in【texts】and filter out results */
    void filter(@NonNull T key, List<? extends T> texts);
}

/** find a key in a List of CharSequence and filter out result */
public interface FilterText extends Filter<CharSequence> {}


/** select stuff */
public interface SelectorRaw //════════════════════════════════════════════════════  SelectorRaw i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   /** set selector from array */
    void        setSelected (boolean[] b);
    /** @return array of selected pos */
    boolean[]   getSelected ();
    /** @return true if element at【rawPos】is selected */
    boolean     isSelected  (@RawPos int rawPos);
}
/** advanced select stuff */
public interface Selector extends SelectorRaw, Sizable, IterableE<Integer>
{   void    select      (int pos);
    void    set         (int pos, boolean setTo);
    void    select      (int start, int end);
    /** 【iterator: onSelect】: (@FilterPos int pos) */
    void    select      (int start, int end, IteratorE<Integer> onSelect);
    void    selectSwap  (int pos);
    void    unselect    (int pos);
    void    selectAll   (boolean setTo);
    void    selectAll   (boolean setTo, IteratorE<Integer> iterator);
    
    void setSize    (int maxItem);
    void setSize    (Sizable size);
    void changeSize (int offset);
    
    void fill(boolean with);
    int getSelectedCount();
    /** Iterate through all selected (and filtered) items<p>
     * 【iterator】: (@FilterPos int pos) */
    void iter(IteratorE<Integer> iterator);
}

/** a Selector that works with a Filter */
public interface SelectorFilter extends Selector
{   void setFilter(FilterRaw filter);
}

/** can get Selector */
public interface SelectorGet { Selector getSelector(); }

public interface PriStList extends Sizable, Printable, Removable<Object>, Iterable<Object> //══════════  PriStList i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void     add     (int     e);
    void     add     (boolean e);
    void     add     (String  e);
    void     add     (Object  o);
    
    int      getInt  (int Index);
    boolean  getBoo  (int Index);
    String   getStr  (int Index);
    
    void set(int index, int      Int);
    void set(int index, boolean  boo);
    void set(int index, String   str);
    
    Object  remove      (int index);
    int     removeRange (int start, int end);
    /**【iterator】: RemoverIterator (int index, T e)
     *  @return total number of elements removed */
    int     removeIter  (IteratorBoo<Object> removerIterator);
    
    int indexOf(int     e);
    int indexOf(boolean e);
    int indexOf(String  e);
    
    boolean isContain(int     e);
    boolean isContain(boolean e);
    boolean isContain(String  e) ;
    
    void    clear();
    boolean isEmpty();
}

/** can remove an element at【index】and return【T】*/
public interface Removable<T>   { T remove(int index);  } //══════════════════════  Removable<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface Closable       { void close();         } //══════════════════════════  Closable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface Openable       { void open();         } //═══════════════════════════  Openable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface ClosableAdv extends Closable { boolean isClosed(); }

//██████████████████████████████████████████████████████████████████████████████████████████████████  ABLE INTERFACE
public interface ArrayableAbs {} //═══════════════════════════════════════════════  Arrayable<E> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface ArrayablePri<E>    extends ArrayableAbs { E    toArr(); }
public interface Arrayable<E>       extends ArrayableAbs { E[]  toArr(); }
public interface Printable  { void print(); }
public interface Deletable  { void delete(); }
public interface Resettable { void reset(); }
public interface Killable   { void kill(); }
public interface Destroyable{ void destroy(); }

public interface ScalableRaw<T>                     { T scale   (float scale); }
public interface Scalable<T> extends ScalableRaw<T> { T scale100(float scale); }


public interface Resetter { void setResettable(Resettable resettable); }

public interface Remover extends Buildable //══════════════════════════════════════════  Remover i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   Remover set(Sin.Selector selector);
    Remover set(Remover remover);
    boolean[] get();
    /** build using another Remover data */
    void build(Remover remover);
}

public interface Savable    { void save(); } //════════════════════════════════════════  Savable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface Saver      { void save(Savable savable); }

public interface Loadable   { void load(); } //═══════════════════════════════════════  Loadable i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

//═════════════════════════════════════════════════════════════════════════════  CoorOffsetXYSet i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface CoorOffsetXYSet { void setOffset(float offsetX, float offsetY); }
public interface CoorOffsetXYGet { int getXOffset(); int getYOffset(); }

public interface Surface { <T>void setEngine(T engine); } //═══════════════════════════  Surface i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface SqlSettingsAbs
{   Cursor getAllCursor();
    void deleteTable();
    void renameTable(String OLD, String NAME);
    void resetTable();
    int getRowCount();
    void delete(String KEY, String keyValue);
    void delete(String keyValue);
    void deleteAll();
}

public interface SqlSettings extends SqlSettingsAbs
{   void add(String name, Object value);
    void add(String name, int value);
    void add(String name, boolean value);
    void add(String name, String value);
    
    void add(Sin.Pair<String, Object> pair, int... index);
    void add(Sin.Pair<String, Object> pair, int index);
    void add(Sin.Pair<String, Object> pair);
    
    /** @return same type as【def】*/
    Object getByType(String colValue, Object def);
}

public interface AniM
{   boolean start(View v, Anim.Preset preset);
}

public interface ElementGet //══════════════════════════════════════════════════════  ElementGet i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   /** get selected element from a List */
    <T> T getElement(List<T> list);
}

public interface ElementDelete //════════════════════════════════════════════════  ElementDelete i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   /** delete an element from a List */
    <T> void deleteElement(List<T> list);
}

public interface SetupBasic
{   void setup();
    void setupID();
    void setupClasses();
    void setupViews();
    void setupTouchLs();
}

public interface ModularClass extends OnBackPress, OnDestroy, Sin.Closable //═════  ModularClass i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   /** inflate to
 * @param layout*/
    void to(@IdRes int layout);
    /** require data from another module */
    <T extends ModularClass> void require(T... module);
    /** provide data to another module */
    <T> T provide(Class<T> c);
    /** get stuff from module */
    <T> void get(Class<T> c, Get<T> get);
    void setOnClose(Runnable onClose);
}


public interface ActivityGet { Activity getActivity(); } //════════════════════════  ActivityGet i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
/** allow loading ModularClass */
public interface ModuleLoader extends ActivityGet //══════════════════════════════  ModuleLoader i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
{   void onLoadModule(Sin.ModularClass module);
}
/** allow loading ModularClass, auto set onDestroy */
public interface ModuleLoaderAdv extends ModuleLoader, OnDestroyAdder {}

@FunctionalInterface public interface NewIns      <T extends ModularClass> { T newIns(Activity activity); }
@FunctionalInterface public interface NewInsParent<T extends ModularClass> { T newIns(Activity activity, @IdRes int parent); }

/** allow adding onDestroy */
public interface OnDestroyAdder
{   <T extends Sin.OnDestroy> void addOnDestroy(T onDestroy);
    <T extends Sin.OnDestroy> void addOnDestroy(List<T> onDestroy);
}

@FunctionalInterface public interface GetInt { int      getInt(); } //══════════════════  GetInt i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
@FunctionalInterface public interface GetBoo { boolean  getBoo(); }

public interface SetI<T>    { void set(T item, int i); } //════════════════════════════  SetI<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂
public interface Set<T>     { void set(T item); }

public interface Get<T>     { void get(T o); } //═══════════════════════════════════════  Get<T> i  ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂

public interface Cropable
{   void setCropArea(Rect rect);
}

public interface Cropper
{   void crop(@Nullable Cropable cropable);
}

} //═══════════════════════════════════════════════════════════════════════════════════════════════════════════════
