package com.ctoutweb.aet.core.paramter.memoryCardGameParameter;

import com.ctoutweb.aet.core.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.usecase.generateNewMemoryCardGame.provider.IDomainModelInstanceProvider;

public class GameData {
  private static final IDomainModelInstanceProvider PROVIDER = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;
  public static final short TIME_TO_OBSERVE_BEFORE_START = 9;
  public static final String[] CONGRATULATION_WORDS = new String[] {"Bravo", "Continue", "Génial", "Au top", "Champion"};
  public static final String[] LOOSING_WORDS = new String[] {"Dommage", "Bouuuuu", "T'es mauvais", "Ouille", "Concentre toi"};
  public static final String GAME_TEXT_PRESENTATION = "Observe et retien les cartes. Tu devra être capable de retrouver toutes les cartes qui te sont demandé. Tu es prêts à jouer ?";
  public static final String GAME_TITLE = "Les cartes mémoire";
  public static final String CARD_TO_FIND_PRESENTATION = "Retrouve cette carte dans le jeu";
  public static final String GAME_VICTORY_GAME = "Bravooooo. Tu es trop fort. N'hésite pas à recommencer";

  // Chargement text de fin
  public static IGameEndText END_TEXT_EXCELLENT =  PROVIDER.provideEndText("Houaaaaa Excellent", "Tu as fais une excellente partie. Continue comme ça");
  public static IGameEndText END_TEXT_VERY_GOOD =  PROVIDER.provideEndText("Bravooooo", "Tu as fais une trés bonne partie. ne t'arrète pas");
  public static IGameEndText END_TEXT_GOOD =  PROVIDER.provideEndText("C'est bien", "Tu as fais une belle partie. Continue comme ça");
  public static IGameEndText END_TEXT_MEDIUM =  PROVIDER.provideEndText("Pas mal", "Tu t'en sors bien. Ne baisse pas les bras");
  public static IGameEndText END_TEXT_BAD =  PROVIDER.provideEndText("Ouchhhh", "C'est dur on dirait. Continue à te concentrer");
  public static IGameEndText END_TEXT_VERY_BAD =  PROVIDER.provideEndText("Ouilleeeeee", "Tu as souffert j'ai l'impression. Concentre toi, tu y arriveras");
  public static IGameEndText END_TEXT_LOOSE =  PROVIDER.provideEndText("Dommageeee", "Alors c'est pas dur de perdre?");

  public static final String GAME_LOOSE_TEXT = "C'est pas grave. Tu peux encore recommencer et t'améliorer";
}
