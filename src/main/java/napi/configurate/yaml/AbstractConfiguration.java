package napi.configurate.yaml;

import com.google.common.reflect.TypeToken;
import napi.configurate.yaml.source.ConfigSource;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.ConfigurationVisitor;
import ninja.leaping.configurate.ValueType;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractConfiguration implements ConfigurationNode {

    protected final ConfigSource source;
    protected final YAMLConfigurationLoader loader;
    protected ConfigurationNode root;

    AbstractConfiguration(ConfigSource source, YAMLConfigurationLoader loader) {
        this.source = source;
        this.loader = loader;
    }

    /* Delegated methods */

    public static ConfigurationNode root() {
        return ConfigurationNode.root();
    }

    public static ConfigurationNode root(Consumer<ConfigurationNode> action) {
        return ConfigurationNode.root(action);
    }

    public static ConfigurationNode root( ConfigurationOptions options) {
        return ConfigurationNode.root(options);
    }


    public static ConfigurationNode root( ConfigurationOptions options, Consumer<ConfigurationNode> action) {
        return ConfigurationNode.root(options, action);
    }

    @Override
    public Object getKey() {
        return root.getKey();
    }

    @Override
    public Object[] getPath() {
        return root.getPath();
    }

    @Override
    public ConfigurationNode getParent() {
        return root.getParent();
    }

    @Override
    public ConfigurationNode getNode( Object  ... path) {
        return root.getNode(path);
    }

    @Override
    public ConfigurationNode getNode( Iterable<?> path) {
        return root.getNode(path);
    }

    @Override
    public boolean isVirtual() {
        return root.isVirtual();
    }

    @Override
    public  ConfigurationOptions getOptions() {
        return root.getOptions();
    }

    @Override
    @Deprecated
    public ValueType getValueType() {
        return root.getValueType();
    }

    @Override
    public boolean isList() {
        return root.isList();
    }

    @Override
    public boolean isMap() {
        return root.isMap();
    }

    @Override
    @Deprecated
    public boolean hasListChildren() {
        return root.hasListChildren();
    }

    @Override
    @Deprecated
    public boolean hasMapChildren() {
        return root.hasMapChildren();
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public List<? extends ConfigurationNode> getChildrenList() {
        return root.getChildrenList();
    }

    @Override
    public Map<Object, ? extends ConfigurationNode> getChildrenMap() {
        return root.getChildrenMap();
    }

    @Override
    public Object getValue() {
        return root.getValue();
    }

    @Override
    public Object getValue( Object def) {
        return root.getValue(def);
    }

    @Override
    public Object getValue( Supplier<Object> defSupplier) {
        return root.getValue(defSupplier);
    }

    @Override
    public <T>  T getValue( Function<Object, T> transformer) {
        return root.getValue(transformer);
    }

    @Override
    public <T> T getValue( Function<Object, T> transformer,  T def) {
        return root.getValue(transformer, def);
    }

    @Override
    public <T> T getValue( Function<Object, T> transformer,  Supplier<T> defSupplier) {
        return root.getValue(transformer, defSupplier);
    }

    @Override
    public  <T> List<T> getList( Function<Object, T> transformer) {
        return root.getList(transformer);
    }

    @Override
    public <T> List<T> getList( Function<Object, T> transformer,  List<T> def) {
        return root.getList(transformer, def);
    }

    @Override
    public <T> List<T> getList( Function<Object, T> transformer,  Supplier<List<T>> defSupplier) {
        return root.getList(transformer, defSupplier);
    }

    @Override
    public  <T> List<T> getList(TypeToken<T> type) throws ObjectMappingException {
        return root.getList(type);
    }

    @Override
    public <T> List<T> getList(TypeToken<T> type,  List<T> def) throws ObjectMappingException {
        return root.getList(type, def);
    }

    @Override
    public <T> List<T> getList(TypeToken<T> type,  Supplier<List<T>> defSupplier) throws ObjectMappingException {
        return root.getList(type, defSupplier);
    }

    @Override
    public String getString() {
        return root.getString();
    }

    @Override
    public String getString( String def) {
        return root.getString(def);
    }

    @Override
    public float getFloat() {
        return root.getFloat();
    }

    @Override
    public float getFloat(float def) {
        return root.getFloat(def);
    }

    @Override
    public double getDouble() {
        return root.getDouble();
    }

    @Override
    public double getDouble(double def) {
        return root.getDouble(def);
    }

    @Override
    public int getInt() {
        return root.getInt();
    }

    @Override
    public int getInt(int def) {
        return root.getInt(def);
    }

    @Override
    public long getLong() {
        return root.getLong();
    }

    @Override
    public long getLong(long def) {
        return root.getLong(def);
    }

    @Override
    public boolean getBoolean() {
        return root.getBoolean();
    }

    @Override
    public boolean getBoolean(boolean def) {
        return root.getBoolean(def);
    }

    @Override
    public <T> T getValue(TypeToken<T> type) throws ObjectMappingException {
        return root.getValue(type);
    }

    @Override
    public <T> T getValue(TypeToken<T> type, T def) throws ObjectMappingException {
        return root.getValue(type, def);
    }

    @Override
    public <T> T getValue(TypeToken<T> type,  Supplier<T> defSupplier) throws ObjectMappingException {
        return root.getValue(type, defSupplier);
    }

    @Override
    public ConfigurationNode setValue( Object value) {
        return root.setValue(value);
    }

    @Override
    public <T> ConfigurationNode setValue(TypeToken<T> type,  T value) throws ObjectMappingException {
        return root.setValue(type, value);
    }

    @Override
    public ConfigurationNode mergeValuesFrom( ConfigurationNode other) {
        return root.mergeValuesFrom(other);
    }

    @Override
    public boolean removeChild( Object key) {
        return root.removeChild(key);
    }

    @Override
    @Deprecated
    public ConfigurationNode getAppendedNode() {
        return root.getAppendedNode();
    }

    @Override
    public ConfigurationNode appendListNode() {
        return root.appendListNode();
    }

    @Override
    public ConfigurationNode copy() {
        return root.copy();
    }

    @Override
    public ConfigurationNode act(Consumer<? super ConfigurationNode> action) {
        return root.act(action);
    }

    @Override
    public <S, T, E extends Exception> T visit(ConfigurationVisitor<S, T, E> visitor) throws E {
        return root.visit(visitor);
    }

    @Override
    public <S, T, E extends Exception> T visit(ConfigurationVisitor<S, T, E> visitor, S state) throws E {
        return root.visit(visitor, state);
    }

    @Override
    public <S, T> T visit(ConfigurationVisitor.Safe<S, T> visitor) {
        return root.visit(visitor);
    }

    @Override
    public <S, T> T visit(ConfigurationVisitor.Safe<S, T> visitor, S state) {
        return root.visit(visitor, state);
    }

}
