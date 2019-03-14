package _01TextEditor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.1.2019 г.
 * Time: 18:22 ч.
 */
public class TextEditorImpl implements TextEditor {

    private Map<String, Boolean> userIsLogged;
    private Trie<Deque<StringBuilder>> userTexts;

    public TextEditorImpl() {
        this.userIsLogged = new LinkedHashMap<>();
        this.userTexts = new Trie<>();
    }


    private boolean isNotLogged(String username) {
        return !this.userIsLogged.containsKey(username)
                || !this.userIsLogged.get(username);
    }

    public Object[] getTextsValueAndLastValue(String username) {

        if (isNotLogged(username)) return null;

        Deque<StringBuilder> textsValue = this.userTexts
                .getValue(username);
        assert textsValue.peek() != null;
        StringBuilder lastValue = new StringBuilder(textsValue.peek());

        return new Object[]{textsValue, lastValue};
    }

    @Override
    public void login(String username) {

        Deque<StringBuilder> textChanges = new ArrayDeque<>();
        textChanges.push(new StringBuilder());

        this.userTexts.insert(username, textChanges);

        this.userIsLogged.put(username, true);
    }

    @Override
    public void logout(String username) {
        if (isNotLogged(username)) return;

        Deque<StringBuilder> textChanges = new ArrayDeque<>();
        textChanges.push(new StringBuilder());
        this.userTexts.insert(username, textChanges);

        this.userIsLogged.put(username, false);
    }

    @Override
    public void prepend(String username, String string) {
        this.insert(username, 0, string);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insert(String username, int index, String string) {

        Object[] textsValueAndLastValue = getTextsValueAndLastValue(username);

        try {
            Deque<StringBuilder> textsValue = (Deque<StringBuilder>) textsValueAndLastValue[0];
            StringBuilder lastValue = (StringBuilder) textsValueAndLastValue[1];

            lastValue.insert(index, string
                    .replace("\"", ""));
            textsValue.push(lastValue);
        } catch (StringIndexOutOfBoundsException
                | NullPointerException ignored) {
            ;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void substring(String username, int startIndex, int length) {

        Object[] textsValueAndLastValue = getTextsValueAndLastValue(username);

        try {
            Deque<StringBuilder> textsValue = (Deque<StringBuilder>) textsValueAndLastValue[0];
            StringBuilder lastValue = (StringBuilder) textsValueAndLastValue[1];

            String substring = lastValue
                    .substring(startIndex, startIndex + length);
            textsValue.push(new StringBuilder(substring));
        } catch (StringIndexOutOfBoundsException
                | NullPointerException ignored) {
            ;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(String username, int startIndex, int length) {

        Object[] textsValueAndLastValue = getTextsValueAndLastValue(username);

        try {
            Deque<StringBuilder> textsValue = (Deque<StringBuilder>) textsValueAndLastValue[0];
            StringBuilder lastValue = (StringBuilder) textsValueAndLastValue[1];

            StringBuilder replaced = lastValue
                    .replace(startIndex, startIndex + length, "");
            textsValue.push(replaced);
        } catch (StringIndexOutOfBoundsException
                | NullPointerException ignored) {
            ;
        }
    }

    @Override
    public void clear(String username) {
        this.substring(username, 0, 0);
    }

    @Override
    public int length(String username) {
        return this.print(username).length();
    }

    @Override
    public String print(String username) {
        if (isNotLogged(username)) return "";

        Deque<StringBuilder> textsValue = this.userTexts
                .getValue(username);
        StringBuilder lastValue = textsValue.peek();

        assert lastValue != null;
        return lastValue.toString();
    }

    @Override
    public void undo(String username) {
        if (isNotLogged(username)) return;

        Deque<StringBuilder> textsValue = this.userTexts
                .getValue(username);

        if (textsValue.size() > 1) {
            textsValue.pop();
        }
    }

    @Override
    public Iterable<String> users(String prefix) {

        List<String> loggedUsersByTime = this.userIsLogged.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (prefix.isEmpty()) {
            return loggedUsersByTime;
        }

        Trie<String> users = new Trie<>();
        loggedUsersByTime.forEach(u -> users.insert(u, ""));

        Iterable<String> usersByPrefix = users.getByPrefix(prefix);

        Collection<String> usersByPrefixCollection = new ArrayList<>();
        usersByPrefix.forEach(usersByPrefixCollection::add);

        loggedUsersByTime.removeIf(u -> !usersByPrefixCollection.contains(u));

        return loggedUsersByTime;
    }
}