package OOAD;

import java.util.ArrayList;
import java.util.Random;

public class NewGame {
	public Organism[][] backBoard = new Organism[20][20];
	private int round = 0;
	private int noOfBugs = 0;
	private int noOfAnts = 0;
	private ArrayList<Ants> antsArray = new ArrayList<Ants>();
	private ArrayList<Bugs> bugsArray = new ArrayList<Bugs>();

	private boolean game_End = false;
	private Random random = new Random();

	NewGame() {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			if (backBoard[x][y] != null) {
				i--;
			} else {
				noOfAnts++;
				Location tempLo = new Location(x, y);
				backBoard[x][y] = new Ants("Ant" + noOfAnts);
				((Ants) backBoard[x][y]).setLocation(tempLo);
				antsArray.add((Ants) backBoard[x][y]);
			}
		}
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			if (backBoard[x][y] != null) {
				i--;
			} else {
				noOfBugs++;
				Location tempLo = new Location(x, y);
				backBoard[x][y] = new Bugs("Bug" + noOfBugs);
				((Bugs) backBoard[x][y]).setLocation(tempLo);
				bugsArray.add((Bugs) backBoard[x][y]);
			}
		}
	}

	public void starve() {
		for (int i = 0; i < bugsArray.size(); i++)
			if (bugsArray.get(i).getStarveRound() == 3) {
				backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
						.getCoor_y()] = null;
				bugsArray.remove(i);
			}
	}

	// for ant to breed
	public void antBreeding() {
		int breedingAnts = antsArray.size();
		for (int i = 0; i < breedingAnts; i++) {
			if (!antsArray.get(i).isNewBreed()) {
				Location tempLoc = backBoard[antsArray.get(i).getLocation().getCoor_x()][antsArray.get(i).getLocation()
						.getCoor_y()].checkBreed(antsArray.get(i).getLocation().getCoor_x(),
								antsArray.get(i).getLocation().getCoor_y(), backBoard);
				if (tempLoc != null) {
					noOfAnts++;
					backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()] = new Ants("Ant" + noOfAnts);
					Location tempLo = new Location(tempLoc.getCoor_x(), tempLoc.getCoor_y());
					((Ants) backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()]).setLocation(tempLo);
					((Ants) backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()]).setNewBreed(true);
					;
					antsArray.add((Ants) backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()]);
				}
			}
		}
	}

	// for bug to breed
	public void bugBreeding() {
		int breedingBugs = bugsArray.size();
		for (int i = 0; i < breedingBugs; i++) {
			if (!bugsArray.get(i).isNewBreed()) {
				Location tempLoc = backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
						.getCoor_y()].checkBreed(bugsArray.get(i).getLocation().getCoor_x(),
								bugsArray.get(i).getLocation().getCoor_y(), backBoard);
				if (tempLoc != null) {
					noOfBugs++;
					backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()] = new Bugs("Bugs" + noOfBugs);
					Location tempLo = new Location(tempLoc.getCoor_x(), tempLoc.getCoor_y());
					((Bugs) backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()]).setLocation(tempLo);
					((Bugs) backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()]).setNewBreed(true);
					;
					bugsArray.add((Bugs) backBoard[tempLoc.getCoor_x()][tempLoc.getCoor_y()]);
				}
			}
		}
	}

	public void resetBugsBreed() {
		for (int i = 0; i < bugsArray.size(); i++) {
			bugsArray.get(i).setNewBreed(false);
		}
	}

	public void resetAntsBreed() {
		for (int i = 0; i < antsArray.size(); i++) {
			antsArray.get(i).setNewBreed(false);
		}
	}

	public void resetBugsMove() {
		for (int i = 0; i < bugsArray.size(); i++) {
			bugsArray.get(i).setMoved(false);
		}
	}

	public void resetAntsMove() {
		for (int i = 0; i < antsArray.size(); i++) {
			antsArray.get(i).setMoved(false);
		}
	}

	public void antsMove() {
		for (int i = 0; i < antsArray.size(); i++) {
			if (!antsArray.get(i).isMoved()) {
				int tempX = antsArray.get(i).getLocation().getCoor_x() + random.nextInt(2) - 1;
				int tempY = antsArray.get(i).getLocation().getCoor_y() + random.nextInt(2) - 1;
				if (antsArray.get(i).checkMovement(antsArray.get(i).getLocation().getCoor_x(),
						antsArray.get(i).getLocation().getCoor_y(), tempX, tempY)) {
					if (backBoard[tempX][tempY] == null) {
						backBoard[tempX][tempY] = backBoard[antsArray.get(i).getLocation().getCoor_x()][antsArray.get(i)
								.getLocation().getCoor_y()];
						backBoard[antsArray.get(i).getLocation().getCoor_x()][antsArray.get(i).getLocation()
								.getCoor_y()] = null;
						((Ants) backBoard[tempX][tempY]).setMoved(true);
						((Ants) backBoard[tempX][tempY]).getLocation().setCoor_x(tempX);
						((Ants) backBoard[tempX][tempY]).getLocation().setCoor_y(tempY);
						antsArray.get(i).setMoved(true);
						antsArray.get(i).getLocation().setCoor_x(tempX);
						antsArray.get(i).getLocation().setCoor_y(tempY);
					}
				}

			}
		}
	}

	public void bugsMove() {
		for (int i = 0; i < bugsArray.size(); i++) {
			if (!bugsArray.get(i).isMoved()) {
				Location tempLocA = new Location(bugsArray.get(i).getLocation().getCoor_x(),
						bugsArray.get(i).getLocation().getCoor_y() + 1); // top
				Location tempLocB = new Location(bugsArray.get(i).getLocation().getCoor_x(),
						bugsArray.get(i).getLocation().getCoor_y() - 1); // btm
				Location tempLocC = new Location(bugsArray.get(i).getLocation().getCoor_x() - 1,
						bugsArray.get(i).getLocation().getCoor_y()); // left
				Location tempLocD = new Location(bugsArray.get(i).getLocation().getCoor_x() + 1,
						bugsArray.get(i).getLocation().getCoor_y()); // right
				Location tempLocE = new Location(bugsArray.get(i).getLocation().getCoor_x() + random.nextInt(2) - 1,
						bugsArray.get(i).getLocation().getCoor_y() + random.nextInt(2) - 1); // rand
				if (prey(tempLocA, i)) {
					antsArray.remove((Ants)backBoard[tempLocA.getCoor_x()][tempLocA
							.getCoor_y()]);
					backBoard[tempLocA.getCoor_x()][tempLocA
							.getCoor_y()] = backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i)
									.getLocation().getCoor_y()];
					backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
							.getCoor_y()] = null;
					((Bugs) backBoard[tempLocA.getCoor_x()][tempLocA.getCoor_y()]).getLocation()
							.setCoor_x(tempLocA.getCoor_x());
					((Bugs) backBoard[tempLocA.getCoor_x()][tempLocA.getCoor_y()]).getLocation()
							.setCoor_x(tempLocA.getCoor_y());
					((Bugs) backBoard[tempLocA.getCoor_x()][tempLocA.getCoor_y()]).setMoved(true);
					bugsArray.get(i).setMoved(true);
					bugsArray.get(i).getLocation().setCoor_x(tempLocA.getCoor_x());
					bugsArray.get(i).getLocation().setCoor_y(tempLocA.getCoor_y());
					bugsArray.get(i).plusAntsKill();
					bugsArray.get(i).reserStarveRound();

				} else if (prey(tempLocB, i)) {
					antsArray.remove((Ants)backBoard[tempLocB.getCoor_x()][tempLocB
					                           							.getCoor_y()]);
					backBoard[tempLocB.getCoor_x()][tempLocB
							.getCoor_y()] = backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i)
									.getLocation().getCoor_y()];
					backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
							.getCoor_y()] = null;
					((Bugs) backBoard[tempLocB.getCoor_x()][tempLocB.getCoor_y()]).getLocation()
							.setCoor_x(tempLocB.getCoor_x());
					((Bugs) backBoard[tempLocB.getCoor_x()][tempLocB.getCoor_y()]).getLocation()
							.setCoor_x(tempLocB.getCoor_y());
					((Bugs) backBoard[tempLocB.getCoor_x()][tempLocB.getCoor_y()]).setMoved(true);
					bugsArray.get(i).setMoved(true);
					bugsArray.get(i).getLocation().setCoor_x(tempLocB.getCoor_x());
					bugsArray.get(i).getLocation().setCoor_y(tempLocB.getCoor_y());
					bugsArray.get(i).plusAntsKill();
					bugsArray.get(i).reserStarveRound();


				} else if (prey(tempLocC, i)) {
					antsArray.remove((Ants)backBoard[tempLocC.getCoor_x()][tempLocC
					                           							.getCoor_y()]);
					backBoard[tempLocC.getCoor_x()][tempLocC
							.getCoor_y()] = backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i)
									.getLocation().getCoor_y()];
					backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
							.getCoor_y()] = null;
					((Bugs) backBoard[tempLocC.getCoor_x()][tempLocC.getCoor_y()]).getLocation()
							.setCoor_x(tempLocC.getCoor_x());
					((Bugs) backBoard[tempLocC.getCoor_x()][tempLocC.getCoor_y()]).getLocation()
							.setCoor_x(tempLocC.getCoor_y());
					((Bugs) backBoard[tempLocC.getCoor_x()][tempLocC.getCoor_y()]).setMoved(true);
					bugsArray.get(i).setMoved(true);
					bugsArray.get(i).getLocation().setCoor_x(tempLocC.getCoor_x());
					bugsArray.get(i).getLocation().setCoor_y(tempLocC.getCoor_y());
					bugsArray.get(i).plusAntsKill();
					bugsArray.get(i).reserStarveRound();


				} else if ((prey(tempLocD, i))) {
					antsArray.remove((Ants)backBoard[tempLocD.getCoor_x()][tempLocD
					                           							.getCoor_y()]);
					backBoard[tempLocD.getCoor_x()][tempLocD
							.getCoor_y()] = backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i)
									.getLocation().getCoor_y()];
					backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
							.getCoor_y()] = null;
					((Bugs) backBoard[tempLocD.getCoor_x()][tempLocD.getCoor_y()]).getLocation()
							.setCoor_x(tempLocD.getCoor_x());
					((Bugs) backBoard[tempLocD.getCoor_x()][tempLocD.getCoor_y()]).getLocation()
							.setCoor_x(tempLocD.getCoor_y());
					((Bugs) backBoard[tempLocD.getCoor_x()][tempLocD.getCoor_y()]).setMoved(true);
					bugsArray.get(i).setMoved(true);
					bugsArray.get(i).getLocation().setCoor_x(tempLocD.getCoor_x());
					bugsArray.get(i).getLocation().setCoor_y(tempLocD.getCoor_y());
					bugsArray.get(i).plusAntsKill();
					bugsArray.get(i).reserStarveRound();

				} else if (bugsArray.get(i).checkMovement(bugsArray.get(i).getLocation().getCoor_x(),
						bugsArray.get(i).getLocation().getCoor_y(), tempLocE.getCoor_x(), tempLocE.getCoor_y())) {
					if (backBoard[tempLocE.getCoor_x()][tempLocE.getCoor_y()] == null) {
						backBoard[tempLocE.getCoor_x()][tempLocE
								.getCoor_y()] = backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i)
										.getLocation().getCoor_y()];
						backBoard[bugsArray.get(i).getLocation().getCoor_x()][bugsArray.get(i).getLocation()
								.getCoor_y()] = null;
						((Bugs) backBoard[tempLocE.getCoor_x()][tempLocE.getCoor_y()]).getLocation()
								.setCoor_x(tempLocE.getCoor_x());
						((Bugs) backBoard[tempLocE.getCoor_x()][tempLocE.getCoor_y()]).getLocation()
								.setCoor_x(tempLocE.getCoor_y());
						((Bugs) backBoard[tempLocE.getCoor_x()][tempLocE.getCoor_y()]).setMoved(true);
						bugsArray.get(i).setMoved(true);
						bugsArray.get(i).getLocation().setCoor_x(tempLocE.getCoor_x());
						bugsArray.get(i).getLocation().setCoor_y(tempLocE.getCoor_y());
						bugsArray.get(i).plusStarveRound();
					} else {
						bugsArray.get(i).plusStarveRound();
						bugsArray.get(i).setMoved(true);

					}
					
				}else
				{
					bugsArray.get(i).plusStarveRound();
					bugsArray.get(i).setMoved(true);
				}
			}
		}
	}

	public boolean prey(Location e, int index) {
		if (bugsArray.get(index).checkMovement(bugsArray.get(index).getLocation().getCoor_x(),
				bugsArray.get(index).getLocation().getCoor_y(), e.getCoor_x(), e.getCoor_y())) {
			if (backBoard[e.getCoor_x()][e.getCoor_y()] instanceof Ants) {
				return true;
			}
		}
		return false;
	}

	public void gameFlow() {
		round++;
		System.out.println(round);
		System.out.println(antsArray.size());
		System.out.println(bugsArray.size());
		bugsMove();
		antsMove();
		resetAntsMove();
		resetBugsMove();
		if (round % 3 == 0) {
			antBreeding();
			resetAntsBreed();
		}
		if (round % 8 == 0) {
			bugBreeding();
			resetBugsBreed();
		}
		starve();
		for (int i = 0; i < bugsArray.size(); i++) {
			System.out.println(bugsArray.get(i).getName() + " Kills " + bugsArray.get(i).getAntsKill());
			System.out.println(bugsArray.get(i).getName() + " Starve " + bugsArray.get(i).getStarveRound());
		}
		System.out.println(round);
		System.out.println(antsArray.size());
		System.out.println(bugsArray.size());
		System.out.println();
		System.out.println("**************************************************************************************************************");
		System.out.println();

	}
}
