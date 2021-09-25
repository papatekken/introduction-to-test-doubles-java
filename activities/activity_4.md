 # Activity 4

## Test Doubles - Stub

### 🔎 Exercise 4.1 - Recap - What is a Stub?

Firstly, let's remind ourselves what a Stub is.

- Can you remember what a Stub means?

<details>
<summary>Click here to see the answer</summary>
<pre>

An object designed with methods returning hardcoded/pre-defined responses.
We replace the real-life functionality to provide the values we need for testing purposes.

</pre>
</details>

- In a similar train of thought, when say 'stubbed method', or a 'method has been stubbed',
  we mean that we have forced the return values of the method, so it is returning some pre-defined response.
  
---

### 🔎 Exercise 4.2 - Let's see the Stub in action!

- Head over to the [BubbleTeaRouletteService](../src/main/java/com/techreturners/bubbleteaordersystem/service/BubbleTeaRouletteService.java).

- `getRandomBubbleTeaType` method returns a random type of bubble tea from those available on the `BubbleTeaTypeEnum`.

#### 🤔 Why is this important to know?

- 😬 Oh no! There's an issue here, if the `getRandomBubbleTeaType` is random, we're in a bit of a pickle.
  
---

- Is there something you notice about the Random object and how it's used here?

<details>
<summary>Click to consider the following code</summary>
<pre>

```java
public class BubbleTeaRouletteService {

    private final int SIZE = BubbleTeaTypeEnum.values().length;

    public BubbleTeaRouletteService() {
    }

    public BubbleTeaTypeEnum getRandomBubbleTeaType() {
        Random random = new Random();
        return BubbleTeaTypeEnum.values()[random.nextInt(SIZE)];
    }
    
}

```

- Notice that the statement `Random random = new Random();` is within the `getRandomBubbleTeaType()` method.
- This code will still work as expected, however, for unit testing, it wouldn't be possible to control `Random`
  to provide the values we would like for our test scenarios. This is because every time we call `getRandomBubbleTeaType()`,
  we get a new instance of `Random`.

</pre>
</details>

<details>
<summary>Click to discover an alternative</summary>
<pre>

```java

public class BubbleTeaRouletteService {

  //We define a RANDOM field
  private final Random RANDOM;
  private final int SIZE = BubbleTeaTypeEnum.values().length;

  //Constructor
  //We've chosen to enable the BubbleTeaRouletteService to be set with an object of type Random
  public BubbleTeaRouletteService(Random random) {
    this.RANDOM = random;
  }

  public BubbleTeaTypeEnum getRandomBubbleTeaType() {
    return BubbleTeaTypeEnum.values()[RANDOM.nextInt(SIZE)];
  }
  
}

```
- By changing the `BubbleTeaRouletteService`'s constructor to take in a `Random` object as a parameter, we make
  a `Random` object a dependency. This means that whenever we need to create a `BubbleTeaRouletteService`, we would need
  to provide it with an object of type `Random` to use. A terminology people use for this is Dependency Injection.

</pre>
</details>


---

- The comparisons above show why considering how you test something first before you code can be a handy way of 
  guiding how you can design code that is testable.

- For our testing, we want to make sure we get the same type of Bubble Tea every time we run our test
  (i.e. it's deterministic); however since the method chooses one at random,
  we have no way of ensuring if the method returns a type of Bubble Tea correctly.
  
- A way to resolve this is to use a Stub.
  
---

#### 🗺 Explore the `RandomStub`

- In the [RandomStub](../src/test/java/testhelper/RandomStub.java), we control the return value of the `nextInt` method
using a pre-defined integer value. The `RandomStub` can do this because it extends the `Random` class and overrides
  the `nextInt` method.

- Navigate to the [BubbleTeaRouletteServiceTest](../src/test/java/BubbleTeaRouletteServiceTest.java) file.


- Can you add another unit test so that the `getRandomBubbleTeaType()` method is stubbed to return
`PeachIceTea`?

<details>
<summary>Click here to see a solution</summary>
<pre>

    @Test
    public void shouldGenerateARandomBubbleTeaTypePeachIceTeaWhenGetRandomBubbleTeaTypeIsCalled() {

        //Arrange
        RandomStub randomStub = new RandomStub(BubbleTeaTypeEnum.PeachIceTea.ordinal());
        BubbleTeaRouletteService bubbleTeaRouletteService = new BubbleTeaRouletteService(randomStub);

        //Act
        var actualResult = bubbleTeaRouletteService.getRandomBubbleTeaType();
        var expectedResult = BubbleTeaTypeEnum.PeachIceTea;

        //Assert
        assertEquals(expectedResult, actualResult);

    }

</pre>
</details>

---
### 🥳 Wow! You finished Activity 4! The next activity is the final activity!

You can now head over to [Activity 5 - Test Doubles - Spy](activity_5.md).

---

### Key Study Notes

☑️ A Stub is an object designed with methods returning hardcoded/pre-defined responses. 
We replace the real-life functionality to provide the values we need for testing purposes.
