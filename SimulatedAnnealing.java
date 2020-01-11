package TSP;

public class SimulatedAnnealing {
	private SingleTour best;

	public void simulation() {
		double temperature = 10000;
		double coolingRate = 0.003;

		SingleTour currentSolution = new SingleTour();
		currentSolution.generateIndividual();

		System.out.println("Initial solution distance " + currentSolution.getDistance());

		best = new SingleTour(currentSolution.getTour());

		while (temperature > 1) {
			SingleTour newSolution = new SingleTour(currentSolution.getTour());

			int randomIndex01 = (int) (newSolution.getTourSize() * Math.random());
			City city01 = newSolution.getCity(randomIndex01);

			int randomIndex02 = (int) (newSolution.getTourSize() * Math.random());
			City city02 = newSolution.getCity(randomIndex02);

			newSolution.setCity(randomIndex02, city01);
			newSolution.setCity(randomIndex01, city02);

			double currentEnergy = currentSolution.getDistance();
			double neighbourEnergy = newSolution.getDistance();

			if (acceptanceProbability(currentEnergy, neighbourEnergy, temperature) > Math.random()) {
				currentSolution = new SingleTour(newSolution.getTour());
			}

			if (currentSolution.getDistance() < best.getDistance()) {
				best = new SingleTour(currentSolution.getTour());
			}

			temperature *= 1 - coolingRate;

		}
	}

	private double acceptanceProbability(double currentEnergy, double neighbourEnergy, double temperature) {
		if (neighbourEnergy < currentEnergy)
			return 1;
		return Math.exp((currentEnergy - neighbourEnergy) / temperature);
	}

	public SingleTour getBest() {
		return best;
	}

	public void setBest(SingleTour best) {
		this.best = best;
	}

}
