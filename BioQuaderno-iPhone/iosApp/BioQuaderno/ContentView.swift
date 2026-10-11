import SwiftUI
import ComposeApp

/// Ponte tra SwiftUI e l'interfaccia Compose Multiplatform:
/// qui mostriamo la stessa AppRoot della versione Android.
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard) // Compose gestisce da sé la tastiera
    }
}
