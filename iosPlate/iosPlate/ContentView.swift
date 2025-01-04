import SwiftUI
import shared

@MainActor
class HomeViewModelWrapper: ObservableObject {
    let homeViewModel: HomeViewModel
    @Published var vehicleState: VehicleState? = nil
    
    init() {
        self.homeViewModel = HomeViewModel()
    }
    
    func startObserving() {
        Task {
            for await state in homeViewModel.vehicleState {
                self.vehicleState = state
            }
        }
    }
}

struct ContentView: View {
    
    @StateObject private var viewModel: HomeViewModelWrapper = .init()

	var body: some View {
        VStack {
            if let vehicleState = viewModel.vehicleState {
                if let vehicle = vehicleState.vehicle {
                    Text("Vehicle: \(vehicle.brand) \(vehicle.engine)")
                }
            }
            
            Button {
                viewModel.homeViewModel.fetchVehicleFromPlate(plate: "GX-318-RV")
            } label: {
                Text("Search for plate")
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
