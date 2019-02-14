package day0103;
// @FunctionalInterface : JDK1.8 - 인터페이스의 추상 method가 하나인지 체크.
@FunctionalInterface // 람다식으로 사용하기 위해 거는 어노테이션
public interface TestLambda {
	public abstract String toDay();
}
